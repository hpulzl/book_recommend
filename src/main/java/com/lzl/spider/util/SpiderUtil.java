package com.lzl.spider.util;

import com.lzl.exception.RecommendException;
import com.lzl.spider.bo.RuleBo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: li_zhilei
 * @Date: create in 09:05 17/5/26.
 * @description:
 */
public class SpiderUtil {
    private final Logger logger = LoggerFactory.getLogger(SpiderUtil.class);
    private Document document;
    private RuleBo ruleBo;

    public SpiderUtil(RuleBo ruleBo){
        this.ruleBo = ruleBo;
        try {
            init();
        } catch (IOException e) {
            logger.error("初始化jsoup失敗");
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        String url = ruleBo.getUrl();
        switch (ruleBo.getPostType()){
            case RuleBo.GET:
                document = Jsoup.connect(url).get();
                break;
            case RuleBo.POST:
                document = Jsoup.connect(url).post();
                break;
            default:
                String urlStr = url.replaceAll("%25","");
                System.out.println("urlStr = " + urlStr);
                document = Jsoup.connect(urlStr).get();
                break;
        }
    }

    public Elements getSelect(){
        if(document == null)
            throw new RecommendException(1,"document 不能为空");
        Elements elements = document.select(ruleBo.getStartTag());
        return elements;
    }
}
