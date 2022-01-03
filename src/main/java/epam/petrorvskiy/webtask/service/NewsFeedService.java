package epam.petrorvskiy.webtask.service;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsFeedService {

    boolean addArticle(Map<String,String> newsDate) throws ServiceException;
    List<NewsFeed> findAllNews() throws ServiceException;
    boolean deleteArticleById(long articleId) throws ServiceException;

    Optional<NewsFeed> takeArticleById(int newsId) throws ServiceException;
}
