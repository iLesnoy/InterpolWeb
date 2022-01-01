package epam.petrorvskiy.webtask.dao.impl;

import epam.petrorvskiy.webtask.dao.ColumnName;
import epam.petrorvskiy.webtask.dao.NewsFeedDao;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.exception.DaoException;
import epam.petrorvskiy.webtask.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class NewsFeedDaoImpl implements NewsFeedDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ARTICLE_ADD = "INSERT INTO news_feed (news_article,users_user_id) values(?,?)";
    private static final String SQL_DELETE_ARTICLE_BY_USERS_ID ="DELETE FROM news_feed WHERE users_user_id =?";
    private static final String SQL_DELETE_ARTICLE_BY_ID ="DELETE FROM news_feed WHERE article_id =?";
    private static final String SQL_TAKE_ARTICLE_BY_ID ="SELECT news_article FROM news_feed WHERE article_id =?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean addArticle(NewsFeed article) throws DaoException {
        boolean articleAdded = false;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ARTICLE_ADD)) {
            statement.setString(1, article.getNewsArticle());
            statement.setLong(2, article.getUserId());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleAdded = true;
                logger.info( "article added" + article);
            } else {
                logger.error( "article was not added");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method addArticle, when we try to add user:" + article, e);
        }
        return articleAdded;
    }

    @Override
    public boolean deleteArticlesByUserId(long userId)throws DaoException {
        boolean articleDeleted = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ARTICLE_BY_USERS_ID)) {
            statement.setLong(1,userId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleDeleted = true;
                logger.info( "deleted article id " + userId);
            } else {
                logger.error( "article was not deleted");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method deleteArticleByUserId, when we try to delete article by users id" + userId, e);
        }
        return articleDeleted;
    }

    @Override
    public boolean deleteArticleById(long articleId) throws DaoException {
        boolean articleDeleted = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ARTICLE_BY_ID)) {
            statement.setLong(1,articleId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleDeleted = true;
                logger.info( "deleted article by id " + articleId);
            } else {
                logger.error( "article was not deleted");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method deleteArticleById, when we try to delete article:" + articleId, e);
        }
        return articleDeleted;
    }

    @Override
    public Optional<NewsFeed> takeArticleById(int newsId) throws DaoException {
        Optional<NewsFeed> optionalNewsFeed;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_TAKE_ARTICLE_BY_ID)) {
            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NewsFeed news = createArticle(resultSet);
                optionalNewsFeed = Optional.of(news);
            } else {
                logger.info( "article was not founded");
                optionalNewsFeed = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method takeArticleById, when we try to take article:" + newsId, e);
        }
        return optionalNewsFeed;
    }


    private NewsFeed createArticle(ResultSet resultSet) throws SQLException {
        String article = resultSet.getString(ColumnName.NEWS_ARTICLE);
        NewsFeed newsFeed = new NewsFeed(article);
        return newsFeed;
    }
}
