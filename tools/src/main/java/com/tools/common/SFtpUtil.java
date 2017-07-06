package com.tools.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * FTP发送报文文件 FTP下载报文文件
 */
public class SFtpUtil {
	// ftp客户端对象
	private ChannelSftp sftp = null;
	private int port;
	private String remotePath;// 目标路径
	private String localPath;// 本地路径
	private String host;
	private String userName;
	private String password;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private static Logger log = Logger.getLogger(SFtpUtil.class);

	public static SFtpUtil getInstance(String str) {// 在配置文件中以某些开始的上传下载配置信息
		SFtpUtil ftp = new SFtpUtil(str);
		if (null == ftp.host) {
			return ftp;
		}
		if (null != ftp.localPath && !"".equals(ftp.localPath)) {
			String lastChar = ftp.localPath.substring(ftp.localPath.length() - 1);
			if (!"/".equals(lastChar)) {
				ftp.localPath += "/";
			}
		}
		if (null != ftp.remotePath && !"".equals(ftp.remotePath)) {
			String lastChar = ftp.remotePath.substring(ftp.remotePath.length() - 1);
			if (!"/".equals(lastChar)) {
				ftp.remotePath += "/";
			}
		}
		return ftp;
	}

	private SFtpUtil(String str) {// 在配置文件中以某些开始的上传下载配置信息
		this.port = Integer.parseInt(PropertiesUtil.getProperty("ftp.properties", str + ".port"));
		this.localPath = PropertiesUtil.getProperty("ftp.properties", str + ".localPath");
		this.remotePath = PropertiesUtil.getProperty("ftp.properties", str + ".remotePath");
		this.host = PropertiesUtil.getProperty("ftp.properties", str + ".host");
		this.userName = PropertiesUtil.getProperty("ftp.properties", str + ".userName");
		this.password = PropertiesUtil.getProperty("ftp.properties", str + ".password");
	}

	/**
	 * sftp连接函数
	 * 
	 * @param server
	 *            ftp server 端IP
	 * @param port
	 *            端口
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @return boolean
	 */
	public void connect() throws Exception {
		String host = this.host;
		String userName = this.userName;
		String password = this.password;
		log.info("sftp连接服务器:" + host + ":" + port + ",用户名：" + userName + ",密码:" + password + "开始");
		try {
			JSch jsch = new JSch();
			jsch.getSession(userName, host, port);
			Session sshSession = jsch.getSession(userName, host, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (Exception e) {
			log.info("sftp连接服务器:" + host + ":" + port + ",用户名：" + userName + ",密码:" + password + "，连接失败");
			log.error("连接sftp失败抛出异常", e);
			throw e;
		}
		log.info("sftp连接服务器:" + host + ":" + port + ",用户名：" + userName + ",密码:" + password + "，连接成功");
	}

	/**
	 * ftp文件上传
	 * 
	 * @param sFileName
	 *            源文件名称
	 * @param dFileName
	 *            目标文件名称
	 * @return boolean
	 */
	public void uploadFile(String sFileName, String dFileName) throws Exception {
		uploadFile(sFileName, dFileName, this.localPath, this.remotePath);
	}

	/**
	 * ftp文件上传
	 * 
	 * @param sFileName
	 *            源文件名称
	 * @param dFileName
	 *            目标文件名称
	 * @param localPath
	 *            本地路径
	 * @param remotePath
	 *            目标路径
	 * @return boolean
	 */
	public void uploadFile(String sFileName, String dFileName, String localPath, String remotePath) throws Exception {
		log.info("sftp上传文件：" + localPath + sFileName + "到" + remotePath + dFileName + "开始");
		try {
			sftp.cd(remotePath);
			File file = new File(localPath + sFileName);
			FileInputStream is = new FileInputStream(file);
			sftp.put(is, file.getName());
			if (!sFileName.equals(dFileName)) {
				sftp.rename(sFileName, dFileName);
			}
			log.info("sftp上传文件：" + localPath + sFileName + "到" + remotePath + dFileName + "成功");
		} catch (Exception e) {
			log.error("sftp上传文件：" + localPath + sFileName + "到" + remotePath + dFileName + "失败", e);
			throw e;
		}
	}

	public boolean uploadFile(String sFileName, String dFileName, String localPath, String remotePath,
			String extraPath) {
		log.info("sftp上传文件：" + localPath + extraPath + "/" + sFileName + "到" + remotePath + extraPath + "/" + dFileName
				+ "开始");
		try {
			Vector content = sftp.ls(remotePath + extraPath);
			if (content == null) {
				sftp.mkdir(remotePath + extraPath);
			}
			try {
				sftp.cd(remotePath + extraPath);
				File file = new File(localPath + extraPath + "/" + sFileName);
				FileInputStream is = new FileInputStream(file);
				sftp.put(is, file.getName());
				if (!sFileName.equals(dFileName)) {
					sftp.rename(sFileName, dFileName);
				}
				log.info("sftp上传文件：" + localPath + sFileName + "到" + remotePath + extraPath + "/" + dFileName + "成功");
				return true;
			} catch (Exception e) {
				log.error("sftp上传文件：" + localPath + sFileName + "到" + remotePath + extraPath + "/" + dFileName + "失败");
				e.printStackTrace();
				return false;
			}
		} catch (SftpException e1) {
			e1.printStackTrace();
			try {
				sftp.mkdir(remotePath + extraPath);
				sftp.cd(remotePath + extraPath);
				File file = new File(localPath + extraPath + "/" + sFileName);
				FileInputStream is = new FileInputStream(file);
				sftp.put(is, file.getName());
				if (!sFileName.equals(dFileName)) {
					sftp.rename(sFileName, dFileName);
				}
				log.info("sftp上传文件：" + localPath + sFileName + "到" + remotePath + extraPath + "/" + dFileName + "成功");
				return true;
			} catch (Exception e) {
				log.error("sftp上传文件：" + localPath + sFileName + "到" + remotePath + extraPath + "/" + dFileName + "失败");
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * ftp下载文件
	 * 
	 * @param sFileName
	 *            源文件名称
	 * @param dFileName
	 *            目标文件名称
	 * @param remotePath
	 *            目标路径
	 * @return boolean
	 */
	public boolean downloadFile(String sFileName, String dFileName, String remotePath) {
		return downloadFile(sFileName, dFileName, remotePath, this.localPath);
	}

	/**
	 * ftp下载文件
	 * 
	 * @param sFileName
	 *            源文件名称
	 * @param dFileName
	 *            目标文件名称
	 * @param remotePath
	 *            目标路径
	 * @param localPath
	 *            本地路径
	 * @return boolean
	 */
	public boolean downloadFile(String sFileName, String dFileName, String remotePath, String localPath) {
		boolean blnFlag = false; // 文件是否存在标识
		log.info("sftp下载文件：" + remotePath + sFileName + "到" + localPath + dFileName + "开始");
		try {
			sftp.cd(remotePath);
			Vector vector = listFiles("./");
			for (int i = 0; i < vector.size(); i++) {
				LsEntry f = (LsEntry) vector.get(i);
				String strLine = f.toString();
				String strFileName = strLine.substring(strLine.lastIndexOf(' ') + 1);
				// log.debug("ls文件名为:" + strFileName);
				if (strLine.substring(0, 1).equals("-") && sFileName.equals(strFileName))
					blnFlag = true;
			}
			if (!blnFlag) {
				log.info("sftp下载文件：" + remotePath + sFileName + "到" + localPath + dFileName + "文件不存在");
				return false;
			}
			File path = new File(localPath);
			File file = new File(localPath + dFileName);
			if (!path.exists()) {
				path.mkdirs();
			}
			sftp.get(sFileName, new FileOutputStream(file));
		} catch (Exception e) {
			log.info("sftp下载文件：" + remotePath + sFileName + "到" + localPath + dFileName + "失败");
			e.printStackTrace();
			return false;
		}
		log.info("sftp下载文件：" + remotePath + sFileName + "到" + localPath + dFileName + "成功");
		return true;
	}

	/**
	 * 关闭ftp连接
	 * 
	 * @return boolean
	 */
	public void close() throws Exception {
		if (sftp != null) {
			sftp.getSession().disconnect();
			sftp.disconnect();
			sftp = null;
		}
	}

	/**
	 * 切换目录
	 * 
	 * @param strPath
	 *            指定目录
	 * @throws SftpException
	 */
	public void cd(String strPath) throws SftpException {
		log.info("sftp切换路径到：" + strPath);
		sftp.cd(strPath);
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param sFilePath
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String sFilePath) throws SftpException {
		return sftp.ls(sFilePath);
	}

	/**
	 * 获取指定目录下文件名称
	 * 
	 * @param sFilePath
	 *            目录
	 * @return 文件名称列表
	 * @throws SftpException
	 */
	public List<String> getFilesFromDir(String sFilePath) throws SftpException {

		List<String> filesList = new ArrayList<String>();
		Vector<LsEntry> vector = listFiles(sFilePath);

		for (LsEntry entry : vector) {
			String strFileName = entry.getFilename();
			if (strFileName.equals(".") || strFileName.equals("..")) {
				continue;
			}
			filesList.add(strFileName);
		}
		return filesList;
	}

	/**
	 * 获取指定目录下文件名称
	 * 
	 * @param sFilePath
	 *            目录
	 * @return 文件名称列表
	 * @throws SftpException
	 */
	public Set<String> getFilesSetFromDir(String sFilePath) throws SftpException {

		Set<String> filesList = new HashSet<String>();
		Vector<LsEntry> vector = listFiles(sFilePath);

		for (LsEntry entry : vector) {
			String strFileName = entry.getFilename();
			if (strFileName.equals(".") || strFileName.equals("..")) {
				continue;
			}
			filesList.add(strFileName);
		}
		return filesList;
	}

	// public static void main(String[] args)
	// {
	// String ip = "123.57.48.237";
	// int port = 22;
	// String user = "appuser";
	// String passwd = "ESjBwnHRJM";
	// String sFilePath = "d:\\";
	// String sFileName = "c.txt";
	// String dFilePath = "ftpDir";
	// String dFileName = "a.txt";
	// try
	// {
	// msFtp ms = new msFtp();
	// boolean bln = ms.connect(ip, port, user, passwd);
	// if (bln)
	// {
	// Vector v = ms.listFiles(dFilePath);
	// for (int i=0;i<v.size();i++)
	// {
	// //system.out.println(v.get(i));
	// }
	// // ms.uploadFile(sFilePath,sFileName,dFilePath,dFileName,"");
	// ms.downloadFile( dFilePath, dFileName,sFilePath, sFileName, "");
	// }
	// ms.colse();
	// }
	// catch(Exception e)
	// {
	// e.getMessage();
	// }
	//
	// }
}
