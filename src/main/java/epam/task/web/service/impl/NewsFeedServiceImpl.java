package epam.task.web.service.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.dao.NewsFeedDao;
import epam.task.web.entity.NewsFeed;
import epam.task.web.exception.DaoException;
import epam.task.web.service.NewsFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static epam.task.web.command.ParameterAndAttribute.*;


public class NewsFeedServiceImpl implements NewsFeedService {

    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedDao newsFeedDao;

    public NewsFeedServiceImpl(NewsFeedDao newsFeedDao) {
        this.newsFeedDao = newsFeedDao;
    }

    @Override
    public boolean addArticle(Map<String,String> newsDate) throws ServiceException {
        boolean articleAdded;
        int articleId = Integer.parseInt(newsDate.get(ARTICLE_ID));
        String newsArticle = newsDate.get(NEWS_ARTICLE);
        long userId = Long.parseLong(newsDate.get(USER_ID));
        NewsFeed newsFeed = new NewsFeed(articleId, newsArticle, userId);
        try {
            articleAdded = newsFeedDao.addArticle(newsFeed);

        } catch (DaoException e) {
            logger.error("dao exception in method add, when we try to add newsArticle:" + newsFeed + ". " + e);
            throw new ServiceException(e);
        }
        return articleAdded;
    }

    @Override
    public boolean deleteArticlesByUserId(long userId) throws ServiceException{
        return false;
    }

    @Override
    public boolean deleteArticleById(long articleId) throws ServiceException{
        return false;
    }

    @Override
    public Optional<NewsFeed> takeArticleById(int newsId) throws  ServiceException {
        Optional<NewsFeed> news;

        try {
            news = newsFeedDao.takeArticleById(newsId);

        } catch (DaoException e) {
            logger.error("service exception in method takeArticleById" + e);
            throw new ServiceException(e);
        }
        return news;
    }
}
