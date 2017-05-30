package com.lzl.spider.task;

import com.lzl.bean.Books;
import com.lzl.service.BooksService;
import com.lzl.spider.bo.RuleBo;
import com.lzl.spider.util.SpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author: li_zhilei
 * @Date: create in 13:52 17/5/27.
 * @description:小说类信息爬取
 */
@PropertySource("classpath:bookResources.properties")
@Component
public class NovelSpiderTask {
    @Value("${novel_url}")
    private String url;
    @Autowired
    private BooksService booksService;

    public void sechdule(){
        RuleBo ruleBo = new RuleBo();
        ruleBo.setPostType(RuleBo.GET);
        ruleBo.setStartTag("subject-item");
        int start = 0;
        while(true){
            ruleBo.setUrl(url+"?start="+start+"&type=T");
            saveBooks(ruleBo);
            start += 20;
            if(start > 980){
                break;
            }
        }
    }
    private void saveBooks(RuleBo ruleBo){
        SpiderUtil spiderUtil = new SpiderUtil(ruleBo);
        Elements elements = spiderUtil.getSelect();
        for (Element element : elements){
            Books books = new Books();
            //img
            Elements img = element.getElementsByTag("img");
            books.setImgUrl(img.attr("src"));
            //title
            Elements title = element.getElementsByTag("a");
            books.setName(title.attr("title"));
            //score
            Elements score = element.getElementsByClass("rating_nums");
            if (StringUtils.isEmpty(score.text())){
                books.setScore(0D);
            }else{
                books.setScore(Double.parseDouble(score.text()));
            }
            //others
            Elements others = element.getElementsByClass("pub");
            parseOthers(others.text());
        }
    }
    private String parseOthers(String str){
        System.out.println("str = " + str);
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }
}
