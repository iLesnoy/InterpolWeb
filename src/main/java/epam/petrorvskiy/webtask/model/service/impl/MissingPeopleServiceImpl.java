package epam.petrorvskiy.webtask.model.service.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;
import epam.petrorvskiy.webtask.model.dao.MissingPeopleDao;
import epam.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import epam.petrorvskiy.webtask.model.service.MissingPeopleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MissingPeopleServiceImpl implements MissingPeopleService {

    private static final Logger logger = LogManager.getLogger();
    private final MissingPeopleDao missingPeopleDao;

    public MissingPeopleServiceImpl(MissingPeopleDaoImpl missingPeopleDao) {
        this.missingPeopleDao = missingPeopleDao;
    }

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
    public Optional<MissingPeople> takeMissedHumanById(MissingPeople people, long id) throws ServiceException {
        Optional<MissingPeople> optionalMissingPeople;
        try {
            optionalMissingPeople = missingPeopleDao.takeMissedHumanById(people,id);
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
