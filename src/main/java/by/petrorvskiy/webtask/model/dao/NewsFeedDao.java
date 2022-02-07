package by.petrorvskiy.webtask.model.dao;


import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


public interface NewsFeedDao {


    boolean addArticle(NewsFeed article, InputStream stream) throws DaoException;

    /**
     * @param article,stream which used for updating
     * @return true if {@link NewsFeed} updated, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean updateArticle(NewsFeed article,InputStream stream) throws DaoException;

    /**
     * @param articleId which used for deleting
     * @return true if {@link NewsFeed} deleted, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean deleteArticleById(long articleId) throws DaoException;

    /**
     * @return collection of all {@link NewsFeed}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<NewsFeed> findAllNews() throws DaoException;

    /**
     * @param newsId which used for searching
     * @return Optional NewsFeed
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<NewsFeed>takeArticleById(long newsId) throws DaoException;



}
