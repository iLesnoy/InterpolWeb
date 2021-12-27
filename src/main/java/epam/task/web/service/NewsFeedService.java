package epam.task.web.service;

import com.google.protobuf.ServiceException;
import epam.task.web.entity.NewsFeed;
import epam.task.web.exception.DaoException;

import java.util.Map;
import java.util.Optional;

public interface NewsFeedService {

    boolean addArticle(Map<String,String> newsDate) throws ServiceException;
    boolean deleteArticlesByUserId(long userId) throws ServiceException;
    boolean deleteArticleById(long articleId) throws ServiceException;

    Optional<NewsFeed> takeArticleById(int newsId) throws ServiceException;
}
