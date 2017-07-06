package com.tools.common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String loadProperties(String url, String propName, String defaultValue) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(url);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        String returnValue = prop.getProperty(propName, defaultValue);
        return returnValue;
    }

    public static String getProperty(String fileName, String key) {
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	if(in != null)
        	{
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        String value = prop.getProperty(key);
        return value;

    }

    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
        	if(in != null)
        	{
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return prop;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Ftp ftp = Ftp.getInstance();

        System.out.println(PropertiesUtil.getProperty("ftp.properties", "offer.host"));
    }
}
