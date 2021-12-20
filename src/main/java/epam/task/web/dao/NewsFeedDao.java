package epam.task.web.dao;

import epam.task.web.entity.NewsFeed;
import epam.task.web.exception.DaoException;

import java.util.Optional;

public interface NewsFeedDao {


    boolean addArticle(NewsFeed article) throws DaoException;
    boolean deleteArticlesByUserId(long userId) throws DaoException;
    boolean deleteArticleById(long articleId) throws DaoException;

    Optional<NewsFeed>takeArticleById(int newsId) throws DaoException;



}
