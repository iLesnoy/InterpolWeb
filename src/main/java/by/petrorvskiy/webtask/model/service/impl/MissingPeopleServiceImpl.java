package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.model.dao.MissingPeopleDao;
import by.petrorvskiy.webtask.model.service.MissingPeopleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MissingPeopleServiceImpl implements MissingPeopleService {

    private static final Logger logger = LogManager.getLogger();
    private final MissingPeopleDao missingPeopleDao = MissingPeopleDaoImpl.getInstance();

    @Override
    public boolean addMissedPeople(MissingPeople people) throws ServiceException {
         boolean addMissedPeople;
        try {
            missingPeopleDao.addMissedPeople(people);
            addMissedPeople = true;
        } catch (DaoException e) {
            logger.info("DaoException in method addMissedPeople" + e);
            throw new ServiceException(e);
        }
        return addMissedPeople;
    }

    @Override
    public boolean updateMissingPeopleById(MissingPeople missingPeople, long id) throws ServiceException {
        boolean updateMissingPeopleById;
        try {
            missingPeopleDao.updateMissingPeopleById(missingPeople,id);
            updateMissingPeopleById = true;
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return updateMissingPeopleById;
    }

    @Override
    public boolean deleteMissedHumanById(long humanId) throws ServiceException {
        boolean deleteMissedHumanById;
        try {
            missingPeopleDao.deleteMissedHumanById(humanId);
            deleteMissedHumanById = true;
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return deleteMissedHumanById;
    }

    @Override
    public List<MissingPeople> findAllMissingPeople() throws ServiceException {
        List<MissingPeople> findAllMissingPeople;
        try {
            findAllMissingPeople = missingPeopleDao.findAllMissingPeople();
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return findAllMissingPeople;
    }

    @Override
    public Optional<MissingPeople> takeMissedHumanById(long id) throws ServiceException {
        Optional<MissingPeople> optionalMissingPeople;
        try {
            optionalMissingPeople = missingPeopleDao.takeMissedHumanById(id);
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return optionalMissingPeople;
    }

    @Override
    public List<MissingPeople> findAllMissingPeopleByName(String name) throws ServiceException {
        List<MissingPeople>missingPeople;
        try {
            missingPeople = missingPeopleDao.findAllMissingPeopleByName(name);
        } catch (DaoException e) {
            logger.info("DaoException in method " + e);
            throw new ServiceException(e);
        }
        return missingPeople;
    }
}
