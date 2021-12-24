package epam.task.web.service.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.dao.NewsFeedDao;
import epam.task.web.dao.UserDao;
import epam.task.web.entity.NewsFeed;
import epam.task.web.entity.User;
import epam.task.web.exception.DaoException;
import epam.task.web.service.NewsFeedService;
import epam.task.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsFeedServiceImpl implements NewsFeedService {

    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedDao newsFeedDao;

    public NewsFeedServiceImpl(NewsFeedDao newsFeedDao) {
        this.newsFeedDao = newsFeedDao;
    }

    @Override
    public boolean addArticle(NewsFeed article) throws ServiceException{
        return false;
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
