package com.yournews.yournews.models;

import java.util.List;

public class Headline {
    private String status;
    private int totalResult;
    private List<Article> article;

    public Headline(String status, int totalResult, List<Article> article) {
        this.status = status;
        this.totalResult = totalResult;
        this.article = article;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public List<Article> getArticle() {
        return article;
    }
}
