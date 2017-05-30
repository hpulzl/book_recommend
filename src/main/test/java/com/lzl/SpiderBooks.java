package com.lzl;


import com.lzl.bean.Book;
import com.lzl.spider.task.DouBanSpiderTask;
import com.lzl.spider.task.NovelSpiderTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 19:25 17/5/22.
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class SpiderBooks {
    @Autowired
    private DouBanSpiderTask douBanSpiderTask;
    @Autowired
    private NovelSpiderTask novelSpiderTask;
    @Test
    public void test(){
        try {
            Document document = Jsoup.connect("https://book.douban.com/").get();
            Elements elements = document.select("div.bd");
            System.out.println("elements.size() = " + elements.size());
            List<Book> list = new ArrayList<Book>();
            Element element = elements.get(3);
            Elements lis = element.getElementsByTag("li");
            System.out.println("lis.size() = " + lis.size());
            for (Element eli : lis){
                Book b = new Book();
                //获取title
                Elements titles = eli.getElementsByClass("title");
                b.setTitle(titles.text());
                //获取a
                Elements img = eli.getElementsByTag("img");
                b.setImageUrl(img.attr("src"));
                //获取author
                Elements author = eli.getElementsByClass("author");
                b.setAuthor(author.text());
                //获取评分
                Elements score = eli.getElementsByClass("average-rating");
                b.setScore(Double.parseDouble(score.text()));
                //获取类型
                Elements type = eli.getElementsByClass("book-list-classification");
                b.setType(type.text());
                list.add(b);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDoban(){
        try {
            Document document = Jsoup.connect("https://www.douban.com/doulist/1264675/").get();
            Elements elements = document.select("div[class=bd doulist-subject]");
            System.out.println("elements.size() = " + elements.size());
            List<Book> list = new ArrayList<Book>();
            for (Element eli : elements){
                Book b = new Book();
                //获取title
                Elements titles = eli.getElementsByClass("title");
                b.setTitle(titles.text());
                //获取a
                Elements img = eli.getElementsByTag("img");
                b.setImageUrl(img.attr("src"));
                //获取评分
                Elements score = eli.getElementsByClass("rating_nums");
                try {
                    b.setScore(Double.parseDouble(score.text()));
                }catch (Exception e){
                    System.out.println("score = " + score);
                    e.printStackTrace();
                }
                //获取类型
                Elements strEle = eli.getElementsByClass("abstract");
                String str = strEle.text();
                System.out.println("str = " + str);

                list.add(b);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDouBanTask(){
        douBanSpiderTask.sechdule();
    }
    @Test
    public void testNovelTask(){
        novelSpiderTask.sechdule();
    }
}
