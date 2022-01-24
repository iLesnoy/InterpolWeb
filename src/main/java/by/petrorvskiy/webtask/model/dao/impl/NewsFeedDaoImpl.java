package by.petrorvskiy.webtask.model.dao.impl;

import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.NewsFeedDao;
import by.petrorvskiy.webtask.model.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class NewsFeedDaoImpl implements NewsFeedDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ARTICLE_ADD = "INSERT INTO news_feed (title,news_article,image) values(?,?,?)";
    private static final String SQL_FIND_ALL_NEWS ="SELECT article_id,title,news_article,image FROM news_feed";
    private static final String SQL_DELETE_ARTICLE_BY_ID ="DELETE FROM news_feed WHERE article_id =?";
    private static final String SQL_TAKE_ARTICLE_BY_ID ="SELECT article_id,title,news_article,image FROM news_feed WHERE article_id =?";
    private static final String SQL_UPDATE_ARTICLE_BY_ID ="UPDATE news_feed SET article_id,title,news_article,image WHERE article_id =?";

    private static final NewsFeedDaoImpl INSTANCE = new NewsFeedDaoImpl();

    public NewsFeedDaoImpl() {
    }

    public NewsFeedDaoImpl getInstance() {
        return INSTANCE;
    }

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addArticle(NewsFeed article, InputStream stream) throws DaoException {
        boolean articleAdded = false;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ARTICLE_ADD)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getNewsArticle());
            statement.setBlob(3, stream);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleAdded = true;
                logger.info( "article added " + article);
            } else {
                logger.error( "article was not added");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method addArticle " + e);
        }
        return articleAdded;
    }

    @Override
    public boolean updateArticle(NewsFeed article,long articleId) throws DaoException {
        boolean updArticle = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ARTICLE_BY_ID)) {
            statement.setInt(1, article.getArticleId());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getNewsArticle());
            statement.setString(4, article.getImage());
            statement.setLong(5, articleId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                updArticle = true;
                logger.info("article updated" + article);
            } else {
                logger.error("article not updated");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method updateArticle, when we try to update article by Id" + article, e);
        }
        return updArticle;
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
            throw new DaoException("Dao exception in method deleteArticleById, when we try to delete article:" + articleId, e);
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
            throw new DaoException("Dao exception in method takeArticleById, when we try to take article:" + newsId, e);
        }
        return optionalNewsFeed;
    }



    private NewsFeed createArticle(ResultSet resultSet) throws SQLException {

        int articleId = resultSet.getInt(ColumnName.ARTICLE_ID);
        String title = resultSet.getString(ColumnName.TITLE);
        String article = resultSet.getString(ColumnName.NEWS_ARTICLE);
        byte[] picture = resultSet.getBytes(ColumnName.IMAGE);
        byte[] encodeImageBytes = Base64.getEncoder().encode(picture);
        String base64Encoded  = new String(encodeImageBytes, StandardCharsets.UTF_8);


        NewsFeed newsFeed = new NewsFeed.NewsFeedBuilder()
                .setArticleId(articleId)
                .setTitle(title)
                .setArticle(article)
                .setImage(base64Encoded).build();
        /*logger.info(newsFeed);*/
        return newsFeed;
    }
}
