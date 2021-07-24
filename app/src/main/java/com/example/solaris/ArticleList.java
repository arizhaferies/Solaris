package com.example.solaris;

public class ArticleList {
    public String articleName;
    public String imageUrl;
    public String description;

    public ArticleList(){

    }

    public ArticleList(String articleName, String imageUrl, String description) {
        this.articleName = articleName;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}
}