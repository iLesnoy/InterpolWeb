package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.impl.SearchApplicationDaoImpl;
import by.petrorvskiy.webtask.model.service.SearchApplicationService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SearchApplicationServiceImpl implements SearchApplicationService {
    private static final Logger logger = LogManager.getLogger();
    private static final SearchApplicationDaoImpl searchApplicationDao = SearchApplicationDaoImpl.getInstance();

    @Override
    public boolean addSearchApplication(SearchApplication application) throws ServiceException {
        logger.debug("findUserPasswordByEmail");
        boolean addSearchApplication;

        try {
            addSearchApplication = searchApplicationDao.addSearchApplication(application);
            logger.debug("addSearchApplication: " + addSearchApplication);

        } catch (DaoException e) {
            logger.error("dao exception in method addSearchApplication" + e);
            throw new ServiceException(e);
        }

        return addSearchApplication;
    }

    @Override
    public boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status, long applicationId) throws ServiceException {
        boolean updateSearchApplicationStatus;

        try {
            updateSearchApplicationStatus = searchApplicationDao.updateSearchApplicationStatus(status, applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return updateSearchApplicationStatus;
    }

    @Override
    public boolean payForApplication(long applicationId, BigDecimal reward) throws ServiceException {
        boolean payForApplication = false;

        try {
            payForApplication = searchApplicationDao.payForApplication(applicationId, reward);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return payForApplication;
    }

    @Override
    public List<SearchApplication> findAllSearchApplications() throws ServiceException {
        List<SearchApplication> searchApplications;

        try {
            searchApplications = searchApplicationDao.findAllSearchApplications();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplications;
    }

    @Override
    public boolean deleteSearchApplicationByUserId(long userId) throws ServiceException {
        boolean searchApplication;
        try {
            searchApplication = searchApplicationDao.deleteSearchApplicationByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplication;
    }

    @Override
    public Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws ServiceException {
        Optional<SearchApplication> searchApplication;

        try {
            searchApplication = searchApplicationDao.takeSearchApplicationById(applicationId);
            if (searchApplication.isPresent()) {
                logger.debug("searchApplication " + searchApplication);
            } else {
                searchApplication = Optional.empty();
            }


        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplication;
    }

    @Override
    public Optional<SearchApplication> findApplicationsByUserId(long userId) throws ServiceException {
        Optional<SearchApplication> searchApplications;
        try {
            searchApplications = searchApplicationDao.findApplicationsByUserId(userId);
            if (searchApplications.isPresent()) {
                logger.debug("searchApplication " + searchApplications);
            } else {
                searchApplications.isEmpty();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplications;
    }
}
