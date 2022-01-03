package epam.petrorvskiy.webtask.entity;

import java.sql.Blob;

public class NewsFeed {

    private int articleId;
    private String title;
    private String newsArticle;
    private String image;


    public NewsFeed() {
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(String newsArticle) {
        this.newsArticle = newsArticle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static class NewsFeedBuilder {
        private final NewsFeed newsFeed;

        public NewsFeedBuilder() {
            newsFeed = new NewsFeed();
        }

        public NewsFeed.NewsFeedBuilder setArticleId(int articleId) {
            newsFeed.setArticleId(articleId);
            return this;
        }

        public NewsFeed.NewsFeedBuilder setTitle(String title) {
            newsFeed.setTitle(title);
            return this;
        }
        public NewsFeed.NewsFeedBuilder setArticle(String article) {
            newsFeed.setNewsArticle(article);
            return this;
        }

        public NewsFeed.NewsFeedBuilder setPicture (String picture) {
            newsFeed.setImage(picture);
            return this;
        }


        public NewsFeed build(){
            return newsFeed;
        }

    }

}

