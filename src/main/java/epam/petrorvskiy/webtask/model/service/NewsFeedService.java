package epam.petrorvskiy.webtask.model.service;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.entity.NewsFeed;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsFeedService {

    boolean addArticle(Map<String, String> newsDate) throws ServiceException;
    boolean updateArticle(NewsFeed article,long articleId) throws ServiceException;
    List<NewsFeed> findAllNews() throws ServiceException;
    boolean deleteArticleById(long articleId) throws ServiceException;
    Optional<NewsFeed>takeArticleById(int newsId) throws ServiceException;
}
