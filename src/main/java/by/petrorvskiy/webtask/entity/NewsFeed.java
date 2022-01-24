package by.petrorvskiy.webtask.entity;

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsFeed newsFeed = (NewsFeed) o;
        if (articleId != newsFeed.articleId) {
            return false;
        }
        if (newsArticle != null ? newsArticle.equals(newsFeed.newsArticle) : newsFeed.newsArticle == null) {
            return false;
        }
        return image != null ? image.equals(newsFeed.image) : newsFeed.image == null;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = articleId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((newsArticle == null) ? 0 : newsArticle.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NewsFeed{");
        sb.append("articleId=").append(articleId);
        sb.append(", title=").append(title);
        sb.append(", newsArticle=").append(newsArticle);
        sb.append(", image=").append(image).append("}");
        return sb.toString();
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

        public NewsFeed.NewsFeedBuilder setImage(String image) {
            newsFeed.setImage(image);
            return this;
        }


        public NewsFeed build(){
            return newsFeed;
        }

    }

}

