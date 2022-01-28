package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface NewsFeedDao {


    boolean addArticle(NewsFeed article, InputStream stream) throws DaoException;
    boolean updateArticle(NewsFeed article,long articleId,InputStream stream) throws DaoException;
    List<NewsFeed> findAllNews() throws DaoException;
    boolean deleteArticleById(long articleId) throws DaoException;
    Optional<NewsFeed>takeArticleById(long newsId) throws DaoException;



}
