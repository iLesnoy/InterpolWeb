package epam.task.web.entity;

public class NewsFeed {

    private long articleId;
    private String newsArticle;
    private long userId;

    public NewsFeed(long articleId, String newsArticle, long userId) {
        this.articleId = articleId;
        this.newsArticle = newsArticle;
        this.userId = userId;
    }

    public NewsFeed(String newsArticle, long userId) {
        this.newsArticle = newsArticle;
        this.userId = userId;
    }

    public NewsFeed() {
    }

    public NewsFeed(String newsArticle) {
        this.newsArticle = newsArticle;
    }


    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(String newsArticle) {
        this.newsArticle = newsArticle;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
