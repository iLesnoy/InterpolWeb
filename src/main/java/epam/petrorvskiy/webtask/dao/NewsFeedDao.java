package epam.petrorvskiy.webtask.dao;

import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.Optional;

public interface NewsFeedDao {


    boolean addArticle(NewsFeed article) throws DaoException;
    boolean deleteArticlesByUserId(long userId) throws DaoException;
    boolean deleteArticleById(long articleId) throws DaoException;

    Optional<NewsFeed>takeArticleById(int newsId) throws DaoException;



}
