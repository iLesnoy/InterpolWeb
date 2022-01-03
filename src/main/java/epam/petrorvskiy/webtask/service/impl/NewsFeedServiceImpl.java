package epam.petrorvskiy.webtask.service.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.dao.NewsFeedDao;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.exception.DaoException;
import epam.petrorvskiy.webtask.service.NewsFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static epam.petrorvskiy.webtask.command.ParameterAndAttribute.*;
import static epam.petrorvskiy.webtask.dao.ColumnName.TITLE;


public class NewsFeedServiceImpl implements NewsFeedService {

    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedDao newsFeedDao;

    public NewsFeedServiceImpl(NewsFeedDao newsFeedDao) {
        this.newsFeedDao = newsFeedDao;
    }

    @Override
    public boolean addArticle(Map<String,String> newsDate) throws ServiceException {
        boolean articleAdded;

        NewsFeed newsFeed = new NewsFeed.NewsFeedBuilder()
                .setTitle(newsDate.get(ParameterAndAttribute.TITLE))
                .setArticle(newsDate.get(ParameterAndAttribute.NEWS_ARTICLE))
                /*.setPicture(newsDate.get(ParameterAndAttribute.IMAGE))*/
                .build();
        try {
            articleAdded = newsFeedDao.addArticle(newsFeed);

        } catch (DaoException e) {
            logger.error("dao exception in method add, when we try to add newsArticle:" + newsFeed + ". " + e);
            throw new ServiceException(e);
        }
        return articleAdded;
    }

    @Override
    public List<NewsFeed> findAllNews() throws ServiceException {
        logger.debug("findAllNews");
        List<NewsFeed> news;
        try {
            news = newsFeedDao.findAllNews();

        } catch (DaoException e) {
            logger.error( "dao exception in method findAllNews" + e);
            throw new ServiceException(e);
        }

        return news;
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
