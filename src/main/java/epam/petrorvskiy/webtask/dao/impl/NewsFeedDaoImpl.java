package epam.petrorvskiy.webtask.dao.impl;

import epam.petrorvskiy.webtask.dao.ColumnName;
import epam.petrorvskiy.webtask.dao.NewsFeedDao;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.exception.DaoException;
import epam.petrorvskiy.webtask.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class NewsFeedDaoImpl implements NewsFeedDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ARTICLE_ADD = "INSERT INTO news_feed (article_id,title,news_article,image) values(?,?,?,?)";
    private static final String SQL_DELETE_ARTICLE_BY_USERS_ID ="DELETE FROM news_feed WHERE users_user_id =?";
    private static final String SQL_FIND_ALL_NEWS ="SELECT article_id,title,news_article,image FROM news_feed";
    private static final String SQL_DELETE_ARTICLE_BY_ID ="DELETE FROM news_feed WHERE article_id =?";
    private static final String SQL_TAKE_ARTICLE_BY_ID ="SELECT article_id,title,news_article,image FROM news_feed WHERE article_id =?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean addArticle(NewsFeed article) throws DaoException {
        boolean articleAdded = false;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ARTICLE_ADD)) {
            statement.setInt(1, article.getArticleId());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getNewsArticle());
            statement.setString(4, article.getImage());
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
    public List<NewsFeed> findAllNews() throws DaoException {
        List<NewsFeed> news = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_NEWS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.add(createArticle(resultSet));
            }
            logger.info(news);

        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method findAllNews, when we try to findAllNews", e);
        }

        return news;
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

        int articleId = resultSet.getInt(ColumnName.ARTICLE_ID);
        String title = resultSet.getString(ColumnName.TITLE);
        String article = resultSet.getString(ColumnName.NEWS_ARTICLE);
        byte[] picture = resultSet.getBytes(ColumnName.IMAGE);
        /*InputStream binaryStream = resultSet.getBinaryStream(ColumnName.IMAGE);*/

        byte[] encodeImageBytes = Base64.getEncoder().encode(picture);
        String base64Encoded  = new String(encodeImageBytes, StandardCharsets.UTF_8);


        NewsFeed newsFeed = new NewsFeed.NewsFeedBuilder()
                .setArticleId(articleId)
                .setTitle(title)
                .setArticle(article)
                .setPicture(base64Encoded)
                .build();
        logger.info(newsFeed.getNewsArticle());
        return newsFeed;
    }
}
