package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.NewsFeedDao;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.model.dao.impl.NewsFeedDaoImpl;
import by.petrorvskiy.webtask.model.service.NewsFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class NewsFeedServiceImpl implements NewsFeedService {

    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedDao newsFeedDao;

    public NewsFeedServiceImpl(NewsFeedDaoImpl newsFeedDao) {
        this.newsFeedDao = newsFeedDao;
    }

    @Override
    public boolean addArticle(Map<String, String> newsDate) throws ServiceException {
        boolean articleAdded;

        NewsFeed newsFeed = new NewsFeed.NewsFeedBuilder()
                .setTitle(newsDate.get(ParameterAndAttribute.TITLE))
                .setArticle(newsDate.get(ParameterAndAttribute.NEWS_ARTICLE))
                .setImage(newsDate.get(ParameterAndAttribute.IMAGE))
                .build();
        try {
            articleAdded = newsFeedDao.addArticle(newsFeed);

        } catch (DaoException e) {
            logger.error("dao exception in method add, when we try to add addArticle:" + newsFeed + ". " + e);
            throw new ServiceException(e);
        }
        return articleAdded;
    }

    @Override
    public boolean updateArticle(NewsFeed article, long articleId) throws ServiceException {
        boolean updateArticle = false;

        try {
            newsFeedDao.updateArticle(article, articleId);
            updateArticle = true;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return updateArticle;
    }

    @Override
    public List<NewsFeed> findAllNews() throws ServiceException {
        logger.debug("findAllNews");
        List<NewsFeed> news;
        try {
            news = newsFeedDao.findAllNews();

        } catch (DaoException e) {
            logger.error("dao exception in method findAllNews" + e);
            throw new ServiceException(e);
        }

        return news;
    }


    @Override
    public boolean deleteArticleById(long articleId) throws ServiceException {
        boolean deleteArticle;
        try {
            deleteArticle = newsFeedDao.deleteArticleById(articleId);
        } catch (DaoException e) {
            logger.error("service exception in method deleteArticleById" + e);
            throw new ServiceException(e);
        }
        return deleteArticle;
    }

    @Override
    public Optional<NewsFeed> takeArticleById(int newsId) throws ServiceException {
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
