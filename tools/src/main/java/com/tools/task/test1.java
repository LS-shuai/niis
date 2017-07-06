package com.tools.task; 

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class test1 {
 public static void main(String[] args) {
  String url = "http://www.56-china.com.cn/index.html";
  Document doc = null;
  try {
   doc = Jsoup.connect(url).get();
  } catch (IOException e1) {
   e1.printStackTrace();
  }
  Element body = doc.body();
  Elements es=doc.select("a");
  System.out.println(es.size());
  for (Iterator it = es.iterator(); it.hasNext();) {
   Element e = (Element) it.next();
   System.out.println(e.attr("abs:href"));
  }
 }
}

 
 