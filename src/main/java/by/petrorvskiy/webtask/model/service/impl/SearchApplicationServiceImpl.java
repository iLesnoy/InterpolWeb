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
    public boolean deleteSearchApplicationByUserId(long userId,long applicationId) throws ServiceException {
        boolean searchApplication;
        try {
            searchApplication = searchApplicationDao.deleteSearchApplicationByUserId(applicationId,userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplication;
    }

    @Override
    public boolean addWantedCriminalApplication(long applicationId, long guiltyId) throws ServiceException {
        boolean addWantedCriminalApplication;
        try {
            addWantedCriminalApplication = searchApplicationDao.addWantedCriminalApplication(applicationId, guiltyId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addWantedCriminalApplication;
    }

    @Override
    public boolean addMissingCriminalApplication(long applicationId, long missingId) throws ServiceException {
        boolean addMissingCriminalApplication;
        try {
            addMissingCriminalApplication = searchApplicationDao.addMissingCriminalApplication(applicationId, missingId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addMissingCriminalApplication;
    }

    @Override
    public long findWantedCriminalGuiltyId(long applicationId) throws ServiceException {
        long criminalGuiltyId;
        try {
            criminalGuiltyId = searchApplicationDao.findWantedCriminalGuiltyId(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return criminalGuiltyId;
    }

    @Override
    public Optional<Long> findApplicationIdByUserId(long userId) throws ServiceException {
        Optional<Long> findApplicationIdByUserId;
        try {
            findApplicationIdByUserId = searchApplicationDao.findApplicationIdByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return findApplicationIdByUserId;
    }

    @Override
    public long findMissingPeopleId(long applicationId) throws ServiceException {
        long missingPeopleId;
        try {
            missingPeopleId = searchApplicationDao.findMissingPeopleId(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return missingPeopleId;
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
    public Optional<SearchApplication> findApplicationByUserIdAndWantedId(long applicationId, long guiltyId) throws ServiceException {
        Optional<SearchApplication> findApplicationByUserIdAndWantedId;
        try {
            findApplicationByUserIdAndWantedId = searchApplicationDao.findApplicationByUserIdAndWantedId(applicationId, guiltyId);
            if (findApplicationByUserIdAndWantedId.isPresent()) {
                logger.debug("searchApplication " + findApplicationByUserIdAndWantedId);
            } else {
                findApplicationByUserIdAndWantedId = Optional.empty();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return findApplicationByUserIdAndWantedId;
    }

    @Override
    public Optional<SearchApplication> findApplicationByUserIdAndMissingId(long applicationId, long missingId) throws ServiceException {
        Optional<SearchApplication> findApplicationByUserIdAndMissingId;
        try {
            findApplicationByUserIdAndMissingId = searchApplicationDao.findApplicationByUserIdAndMissingId(applicationId, missingId);
            if (findApplicationByUserIdAndMissingId.isPresent()) {
                logger.debug("searchApplication " + findApplicationByUserIdAndMissingId);
            } else {
                findApplicationByUserIdAndMissingId = Optional.empty();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return findApplicationByUserIdAndMissingId;
    }

    @Override
    public List<SearchApplication> findApplicationsByUserId(long userId) throws ServiceException {
        List<SearchApplication> searchApplications;
        try {
            searchApplications = searchApplicationDao.findApplicationsByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return searchApplications;
    }
}
