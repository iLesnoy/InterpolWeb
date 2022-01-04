package epam.petrorvskiy.webtask.model.dao;

import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface NewsFeedDao {


    boolean addArticle(NewsFeed article) throws DaoException;
    List<NewsFeed> findAllNews() throws DaoException;
    boolean deleteArticleById(long articleId) throws DaoException;
    Optional<NewsFeed>takeArticleById(int newsId) throws DaoException;



}
