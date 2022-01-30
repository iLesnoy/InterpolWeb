package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.WantedCriminalDao;
import by.petrorvskiy.webtask.model.dao.impl.WantedCriminalDaoImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.WantedCriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WantedCriminalServiceImpl implements WantedCriminalService {

    private static final Logger logger = LogManager.getLogger();
    private final WantedCriminalDao wantedCriminalDao = WantedCriminalDaoImpl.getInstance();


    @Override
    public boolean addWantedCriminal(Map<String, String> criminalData, InputStream photoStream) throws ServiceException {
        boolean addWantedCriminal;
        WantedCriminal criminal = new WantedCriminal.WantedCriminalBuilder()
                .setFirstName(criminalData.get(ParameterAndAttribute.FIRST_NAME))
                .setLastName(criminalData.get(ParameterAndAttribute.LAST_NAME))
                .setCrimCity(criminalData.get(ParameterAndAttribute.CRIME_CITY))
                .setDOB(LocalDate.parse(criminalData.get(ParameterAndAttribute.DATE_OF_BIRTH)))
                .setReward(new BigDecimal(criminalData.get(ParameterAndAttribute.REWARD)))
                .setCrimType(WantedCriminal.CrimeType.valueOf(criminalData.get(ParameterAndAttribute.CRIME_TYPE)))
                .setPhoto(criminalData.get(ParameterAndAttribute.PHOTO))
                .setCrimAdress(ParameterAndAttribute.CRIME_ADDRESS).build();
        try {
            wantedCriminalDao.addWantedCriminal(criminal,photoStream);
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
    public boolean updateWantedCriminalById(Map<String, String> criminalData, InputStream photoStream) throws ServiceException {
        boolean updateWantedCriminalById;

        WantedCriminal criminal = new WantedCriminal.WantedCriminalBuilder()
                .setGuiltyId(Long.valueOf(criminalData.get(ParameterAndAttribute.GUILTY_ID)))
                .setFirstName(criminalData.get(ParameterAndAttribute.FIRST_NAME))
                .setLastName(criminalData.get(ParameterAndAttribute.LAST_NAME))
                .setCrimCity(criminalData.get(ParameterAndAttribute.CRIME_CITY))
                .setDOB(LocalDate.parse(criminalData.get(ParameterAndAttribute.DATE_OF_BIRTH)))
                .setReward(new BigDecimal(criminalData.get(ParameterAndAttribute.REWARD)))
                .setCrimType(WantedCriminal.CrimeType.valueOf(criminalData.get(ParameterAndAttribute.CRIME_TYPE)))
                .setPhoto(criminalData.get(ParameterAndAttribute.PHOTO))
                .setCrimAdress(ParameterAndAttribute.CRIME_ADDRESS).build();
        System.out.println(criminal);
        try {
            wantedCriminalDao.updateWantedCriminalById(criminal,photoStream);
            updateWantedCriminalById = true;
        } catch (DaoException e) {
            logger.info("DaoException in method updateWantedCriminalById" + e);
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
