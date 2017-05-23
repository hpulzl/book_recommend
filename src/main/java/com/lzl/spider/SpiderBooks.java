package com.lzl.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 19:25 17/5/22.
 * @description:
 */
public class SpiderBooks {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://book.douban.com/").get();
            Elements elements = document.getElementsByClass("title");
            List<String> list = new ArrayList<String>();
            for(Element element : elements){
                String text = element.text();
                list.add(text);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
