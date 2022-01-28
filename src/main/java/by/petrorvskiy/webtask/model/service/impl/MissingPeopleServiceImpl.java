package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.dao.MissingPeopleDao;
import by.petrorvskiy.webtask.model.service.MissingPeopleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MissingPeopleServiceImpl implements MissingPeopleService {

    private static final Logger logger = LogManager.getLogger();
    private final MissingPeopleDao missingPeopleDao = MissingPeopleDaoImpl.getInstance();

    @Override
    public boolean addMissedPeople(Map<String, String> missingPeopleDate, InputStream stream) throws ServiceException {
         boolean addMissedPeople;
         MissingPeople people = new MissingPeople.MissingPeopleBuilder()
                 .setName(missingPeopleDate.get(ParameterAndAttribute.USER_NAME))
                 .setSurname(missingPeopleDate.get(ParameterAndAttribute.USER_SURNAME))
                 .setDisappearanceDate(LocalDate.parse(missingPeopleDate.get(ParameterAndAttribute.DISAPPEARANCE_DATE)))
                 .build();


        try {
            missingPeopleDao.addMissedPeople(people,stream);
            addMissedPeople = true;
        } catch (DaoException e) {
            logger.info("DaoException in method addMissedPeople" + e);
            throw new ServiceException(e);
        }
        return addMissedPeople;
    }

    @Override
    public boolean updateMissingPeopleById(Map<String,String> missingData, InputStream stream) throws ServiceException {
        boolean updateMissingPeopleById;
        MissingPeople people = new MissingPeople.MissingPeopleBuilder()
                .setPeopleId(Long.parseLong(missingData.get(ParameterAndAttribute.MISSING_ID)))
                .setName(missingData.get(ParameterAndAttribute.FIRST_NAME))
                .setSurname(missingData.get(ParameterAndAttribute.LAST_NAME))
                .setDisappearanceDate(LocalDate.parse(missingData.get(ParameterAndAttribute.DISAPPEARANCE_DATE)))
                .build();

        try {
            missingPeopleDao.updateMissingPeopleById(people,stream);
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
