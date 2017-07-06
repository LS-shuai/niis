package com.tools.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
	public static void main(String[] args) {
		String url="http://minzu.people.com.cn/GB/165562/167249/10105460.html";
		try {
/*			Document doc=Jsoup.parse(new URL(url), 10000);
			Element el=doc.body();
			Elements els=el.select("a");
			for(Element e:els){
				System.out.println(e.attr("abs:href"));
				
			}*/
			
			
			Document doc1=Jsoup.parse(new URL(url), 10000);
			System.out.println(doc1.text());
			
/*			Document doc=Jsoup.parse(new URL(url), 10000);
			Element el=doc.body();
			Elements els=el.select("img");
			for(Element e:els){
				System.out.println(e.attr("abs:src"));
				
			}
*/			
			
			
			//Elements els=doc.select(".bm_c");
			/*
			Elements bbdas=els.select(".bbda");
			for(Element bbda:bbdas){
				System.out.println(bbda.text());
			}
			
			System.out.println(els.text());*/
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
