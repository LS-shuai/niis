package com.tools.task;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class demo {
    @Test
    public void reasUrl() throws IOException {
        //String url = "http://www.minzu56.net/";
//        String url = "http://www.seac.gov.cn/";
    	String url="https://www.aqistudy.cn//historydata//daydata.php?city=北京&month=201312";
        Connection conn = Jsoup.connect(url); // 建立与url中页面的连接
        Document doc = conn.get(); // 解析页面
        System.out.println(doc.html());
/*        Elements links = doc.select("a[href]"); // 获取页面中所有的超链接
        int i = 1;
        for (Element link : links) { 
               Document doc2 = Jsoup.connect(link.attr("abs:href")).get(); // 解析每篇文章的页面
//               System.out.println("第" + i + "篇：" + doc2.title()); // 把该文章的标题打印出来
               
               Set<String> set=new HashSet<String>(); 
               
//               System.out.println("第" + i + "篇：" + doc2.body().text()); // 把该文章的标题打印出来
               
               i++;
        }
*/    }
}