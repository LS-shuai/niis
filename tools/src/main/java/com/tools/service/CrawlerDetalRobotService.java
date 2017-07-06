package com.tools.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.tools.common.Constant;
import com.tools.dao.CrawlerLinkMapper;
import com.tools.dao.CrawlerRobotMapper;
import com.tools.entity.ToolArticle;
import com.tools.entity.ToolLink;

/**
 * 
 * @ClassName: CrawlerDetalRobotService
 * @Description: 爬虫服务类
 * @author liushuai
 * @date 2017年5月18日 下午12:48:22
 */
@Service
public class CrawlerDetalRobotService {
	private static Logger log = Logger
			.getLogger(CrawlerDetalRobotService.class); 

	@Autowired
	CrawlerLinkMapper crawlerLinkMapper;
	@Autowired
	CrawlerRobotMapper crawlerRobotMapper;

	/**
	 * 爬虫任务
	 * 
	 * @param date
	 * @throws Exception
	 */
	public String execute() throws Exception {
		init();
		int total = doCrawlerReport();
		StringBuilder desc = new StringBuilder();
		desc.append("本次共爬取数据成功条数为：").append(total).append("/n共爬取链接数为：")
				.append(Constant.EXEC_URL_SET.size());
		return desc.toString();
	}
	/**
	 * 预留的初始化函数 （目前没有）
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {

	}

	public int doCrawlerReport() {

		List<ToolLink> list;
		int total = 0;

		// 分页读取报备数据
		list = crawlerLinkMapper.findList();
		if (!list.isEmpty())
			// 多线程实现每一个链接分配一个线程
			for (ToolLink url : list) {
				total += doCrawler(url);
				System.out.println(total);
			}
		return total;
	}

	public static boolean doRemoveUrl(String url) {
		List<String> list = new ArrayList<>();
		list.add("^(?=.*[a-zA-Z])(?=.*\\d{4,}).*$");
		/*
		 * list.add("^[^\\d]+$"); list.add("^.*show.*$");
		 * list.add("^.*case.*$"); list.add("^.*article.*$");
		 */
		for (String regEx : list) {
			Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pat.matcher(url);
			// 字符串是否与正则表达式相匹配
			if (matcher.matches())
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: analyticText
	 * @Description: 完成分析入库操作
	 * @param text
	 * @return
	 * @throws Exception设定文件
	 * @return Boolean返回类型
	 * @UpdateUser-UpdateDate:[liushuai]-[2017年5月21日 下午6:35:52]
	 * @UpdateRemark:[说明本次修改内容]
	 */
	public Boolean analyticText(String title, String text, ToolLink link,
			String url) throws Exception {
		if (text.length() < Constant.MINSIZE) {
			log.info("文章长度少于最低长度:" + url);
			return Constant.EXEC_STATUS_FAILED;
		}
/*		Result parse = BaseAnalysis.parse("孙杨在里约奥运会男子200米自由泳决赛中，以1分44秒65夺得冠军");
		System.out.println(parse);
*/		KeyWordComputer<?> kwc = new KeyWordComputer(5);
		List<Keyword> result = kwc.computeArticleTfidf(title, text);
		StringBuilder keys = new StringBuilder();
		for (Keyword key : result) {
			keys.append(key.getName()).append(" ");
		}

		ToolArticle article = new ToolArticle();
		article.setTitle(title);
		article.setType(link.getType());
		article.setContent(text);
		article.setKeywords(keys.toString());
		article.setLink(url);
		article.setSource(link.getCode());
		try {
			crawlerRobotMapper.insertToolArticle(article);

		} catch (Exception e) {
			log.error("提取关键字失败" + e);
			return Constant.EXEC_STATUS_FAILED;
		}
		return Constant.EXEC_STATUS_SUCCESS;
	}
	public int doCrawler(ToolLink url) {
		Document doc;
		Element el;
		Elements els;
		int total = 0;
		Constant.EXEC_URL_SET.add(null);
		if (Constant.EXEC_URL_SET.add(url.getHref())) {
			try {
				doc = Jsoup.parse(new URL(url.getHref()), 10000); 
				el = doc.body();
				els = el.select("a");
				for (Element e : els) {
					if (Constant.EXEC_URL_SET.add(e.attr("abs:href"))) {
						if (doRemoveUrl(e.attr("abs:href"))) {
							try {
								doc = Jsoup.parse(new URL(e.attr("abs:href")),
										10000);

								if (analyticText(doc.title(), doc.text(),
										url, e.attr("abs:href"))) {
									log.info("爬取成功：" + url.getHref());
									total++;
								}
							} catch (Exception e1) {
								log.error(
										"出现异常" + e1 + "URL:" + url.getHref());;
										System.err.println("continue!!!!!!!!!!!!!!!!!!!!!!!!");
										continue;
								
							}
						} else {
							log.info("url不符合条件已去除" + e.attr("abs:href"));
						}
					} else {
						log.info("url重复" + e.attr("abs:href"));
					}

				}
			} catch (MalformedURLException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		return total;
	}
	
	
	
	
	/*
	 * public void insertTaskLog() throws Exception { Map<String, Object> map =
	 * new HashMap<String, Object>(); // 设置开始时间 map.put("beginTime", new
	 * Date()); // 设置状态为执行中 map.put("state", Constant.EXEC_STATUS_PROCESSING);
	 * map.put("taskName", Constant.AUTO_BD_TASK); map.put("taskCode",
	 * getTaskCode()); // 插入定时任务日志表 detailReportMapper.insertDailyTaskLog(map);
	 * }
	 * 
	 * public void updateTaskLog(Byte state, String message) throws Exception {
	 * Map<String, Object> con = new HashMap<String, Object>();
	 * con.put("taskCode", getTaskCode()); con.put("finishTime", new Date()); //
	 * 设置状态为成功 con.put("state", state); // 插入执行返回结果 con.put("description",
	 * message); detailReportMapper.updateDailyTaskLogByParams(con); }
	 * 
	 * public void updateTaskLog4Redo(Byte state, String message, Date
	 * beginTime) throws Exception { Map<String, Object> con = new
	 * HashMap<String, Object>(); con.put("taskCode", getTaskCode());
	 * con.put("beginTime", beginTime); con.put("finishTime", new Date()); //
	 * 设置状态为成功 con.put("state", state); // 插入执行返回结果 con.put("description",
	 * message); detailReportMapper.updateDailyTaskLogByParams(con); }
	 * 
	 * public String getTaskCode() { SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyyMMdd"); Date date = new Date(); String taskCode =
	 * Constant.AUTO_BD_TASKCODE + "_" + sdf.format(date); return taskCode; }
	 * 
	 *//**
		 * 
		 * @Title: getFailTask
		 * @Description: 查询报备是否失败
		 * @return设定文件
		 * @return int返回类型
		 *//*
		 * public List<Map<String, Object>> getFailInfo() { SimpleDateFormat sdf
		 * = new SimpleDateFormat("yyyyMMdd"); Date date = new Date(); String
		 * taskCode = Constant.AUTO_BD_TASKCODE + "_" + sdf.format(date);
		 * List<Map<String, Object>> taskInfo =
		 * detailReportMapper.selectTaskInfo(taskCode); log.info("TaskCount:" +
		 * taskInfo.size()); return taskInfo; }
		 */
	public static void main(String[] args) throws Exception {
		CrawlerDetalRobotService detailReportService = new CrawlerDetalRobotService();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"springManul.xml");
		CrawlerDetalRobotService manulApp = (CrawlerDetalRobotService) ctx
				.getBean("mApp");
		manulApp.doCrawlerReport();
	}
}
