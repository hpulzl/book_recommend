package com.lzl.spider.util;

import com.lzl.spider.bo.RuleBo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author: li_zhilei
 * @Date: create in 09:05 17/5/26.
 * @description:
 */
public class SpiderUtil {
//    private final int Logger logger = FactoryL
    private Document document;
    private RuleBo ruleBo;

    public SpiderUtil(RuleBo ruleBo){
        this.ruleBo = ruleBo;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        switch (ruleBo.getPostType()){
            case RuleBo.GET:
                document = Jsoup.connect(ruleBo.getUrl()).get();
                break;
            case RuleBo.POST:
                document = Jsoup.connect(ruleBo.getUrl()).post();
                break;
            default:
                document = Jsoup.connect(ruleBo.getUrl()).get();
                break;
        }
    }
    public Elements getSelect(){
        Elements elements = document.select(ruleBo.getStartTag());
        return elements;
    }
}
