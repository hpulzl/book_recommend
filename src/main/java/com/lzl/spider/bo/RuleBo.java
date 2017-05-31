package com.lzl.spider.bo;

/**
 * @Author: li_zhilei
 * @Date: create in 16:13 17/5/25.
 * @description:爬虫工具中的bo类
 */
public class RuleBo {
    public static final int POST = 1;
    public static final int GET = 2;
    public static final int DEFAULT_TYPE = -1;
    private String url; //地址
    private String startTag;//开始标签
    private int postType; //请求类型

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RuleBo{");
        sb.append("url='").append(url).append('\'');
        sb.append(", startTag='").append(startTag).append('\'');
        sb.append(", postType=").append(postType);
        sb.append('}');
        return sb.toString();
    }
}
