package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.NewsFeed;
import com.google.protobuf.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsFeedService {

    boolean addArticle(Map<String, String> newsDate, InputStream stream) throws ServiceException;
    boolean updateArticle(NewsFeed article, long articleId) throws ServiceException;
    List<NewsFeed> findAllNews() throws ServiceException;
    boolean deleteArticleById(long articleId) throws ServiceException;
    Optional<NewsFeed>takeArticleById(int newsId) throws ServiceException;
}
