package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.WantedCriminalDao;
import by.petrorvskiy.webtask.model.dao.impl.WantedCriminalDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.model.service.WantedCriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class WantedCriminalServiceImpl implements WantedCriminalService {

    private static final Logger logger = LogManager.getLogger();
    private final WantedCriminalDao wantedCriminalDao = WantedCriminalDaoImpl.getInstance();


    @Override
    public boolean addWantedCriminal(WantedCriminal criminal, WantedCriminal.CrimType type) throws ServiceException {
        boolean addWantedCriminal;
        try {
            wantedCriminalDao.addWantedCriminal(criminal, type);
            addWantedCriminal = true;
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return addWantedCriminal;
    }

    @Override
    public boolean deleteWantedCriminal(long criminalId) throws ServiceException {
        boolean deleteWantedCriminal;
        try {
            wantedCriminalDao.deleteWantedCriminal(criminalId);
            deleteWantedCriminal = true;
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return deleteWantedCriminal;
    }

    @Override
    public boolean updateWantedCriminalById(WantedCriminal wantedCriminal, long crimId) throws ServiceException {
        boolean updateWantedCriminalById;
        try {
            wantedCriminalDao.updateWantedCriminalById(wantedCriminal, crimId);
            updateWantedCriminalById = true;
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return updateWantedCriminalById;
    }

    @Override
    public List<SearchApplication> findUserSearchApplicationsByUserId(long userId) throws ServiceException {
        return null;
    }

    @Override
    public List<WantedCriminal> findAllWantedCriminals() throws ServiceException {
       List<WantedCriminal> wantedCriminals;
        try {
            wantedCriminals = wantedCriminalDao.findAllWantedCriminals();
        } catch (DaoException e) {
            logger.info("DaoException in method findAllWantedCriminals " + e);
            throw new ServiceException(e);
        }
        return wantedCriminals;
    }

    @Override
    public Optional<WantedCriminal> findAllCriminalsByName(String name) throws ServiceException {
        Optional<WantedCriminal> optionalCriminal;
        try {
            optionalCriminal = wantedCriminalDao.findAllCriminalsByName(name);
        } catch (DaoException e) {
            logger.info("DaoException in method findAllCriminalsByName" + e);
            throw new ServiceException(e);
        }
        return optionalCriminal;
    }

   /* @Override
    public List<SearchApplication> findUserSearchApplicationsByUserId(long userId) throws ServiceException {
        List<SearchApplication> searchApplications;
        try {
            searchApplications = wantedCriminalDao.findUserSearchApplicationsByUserId(userId);
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return searchApplications;
    }*/

    @Override
    public Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws ServiceException {
        Optional<WantedCriminal> optionalWantedCriminal;
        try {
            optionalWantedCriminal = wantedCriminalDao.takeWantedCriminalById(criminalId);
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return optionalWantedCriminal;
    }

    @Override
    public Optional<BigDecimal> findCriminalRewardById(long criminalId) throws ServiceException {
        Optional<BigDecimal> optionalReward;
        try {
            optionalReward = wantedCriminalDao.findCriminalRewardById(criminalId);
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return optionalReward;
    }
}
