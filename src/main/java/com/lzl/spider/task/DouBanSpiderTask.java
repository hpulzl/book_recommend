package com.lzl.spider.task;


import com.lzl.bean.Books;
import com.lzl.spider.bo.RuleBo;
import com.lzl.spider.util.SpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 19:25 17/5/22.
 * @description:豆瓣评分最很高的书籍
 */
@Component
@PropertySource("classpath:bookResources.properties")
public class DouBanSpiderTask {
    @Value("${douban_url}")
    private String url;
    public void sechdule(){
        RuleBo ruleBo = new RuleBo();
        ruleBo.setUrl(url);
        ruleBo.setStartTag("div[class=bd doulist-subject]");
        SpiderUtil spiderUtil = new SpiderUtil(ruleBo);
        Elements elements = spiderUtil.getSelect();
        List<Books> list = new ArrayList<>();
        for (Element element : elements){
            Books b = new Books();
            //获取title
            Elements titles = element.getElementsByClass("title");
            b.setName(titles.text());
            //获取a
            Elements img = element.getElementsByTag("img");
            b.setImgUrl(img.attr("src"));
            //获取评分
            Elements score = element.getElementsByClass("rating_nums");
            try {
                String scoreStr = score.text();
                if(!StringUtils.isEmpty(scoreStr)){
                    b.setScore(Double.parseDouble(scoreStr));
                }
            }catch (Exception e){
                System.out.println("score = " + score);
                e.printStackTrace();
            }
            //获取类型
            Elements strEle = element.getElementsByClass("abstract");
            setAbstractEle(strEle.text());
            list.add(b);
        }
    }
    private void setAbstractEle(String strEle){
        Assert.notNull(strEle,"strEle 信息不能为空");
        String str = strEle.replaceAll(" ",",").replaceAll(":","");
        System.out.println("str = " + str);
        String[] splitStr = str.split(",");
        String[] useArr = new String[]{};
        for (String s : splitStr){
            if(s.equals("作者") || s.equals("")||s.equals("")){

            }
        }
    }
}
