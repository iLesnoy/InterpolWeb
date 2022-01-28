package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsFeedService {

    boolean addArticle(Map<String, String> newsData, InputStream stream) throws ServiceException;
    boolean updateArticle(Map<String,String> newsData, long articleId,InputStream stream) throws ServiceException;
    List<NewsFeed> findAllNews() throws ServiceException;
    boolean deleteArticleById(long articleId) throws ServiceException;
    Optional<NewsFeed>takeArticleById(long newsId) throws ServiceException;
}
