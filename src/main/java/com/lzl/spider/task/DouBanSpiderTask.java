package com.lzl.spider.task;


import com.lzl.spider.bo.RuleBo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: li_zhilei
 * @Date: create in 19:25 17/5/22.
 * @description:豆瓣评分最很高的书籍
 */
@PropertySource("classpath:bookResources.properties")
public class DouBanSpiderTask {
    @Value("douban_url")
    private String url;
    public void sechdule(){
        RuleBo ruleBo = new RuleBo();
        ruleBo.setUrl(url);
        ruleBo.setStartTag("");
    }
}
