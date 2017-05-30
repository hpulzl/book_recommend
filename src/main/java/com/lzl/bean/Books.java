package com.lzl.bean;

import java.util.Date;

public class Books {
    private Integer id;

    private String name;

    private String imgUrl;

    private Double score;

    private String author;

    private String publishCompany;

    private String publishAt;

    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Books{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", imgUrl='").append(imgUrl).append('\'');
        sb.append(", score=").append(score);
        sb.append(", author='").append(author).append('\'');
        sb.append(", publishCompany='").append(publishCompany).append('\'');
        sb.append(", publishAt=").append(publishAt);
        sb.append(", createAt=").append(createAt);
        sb.append('}');
        return sb.toString();
    }
}