package com.alperen.newnews;

import java.util.ArrayList;

public class BaseResponseEntity {
    public String status;
    public int totalResults;
    public ArrayList<ArticleEntity> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleEntity> articles) {
        this.articles = articles;
    }
}
