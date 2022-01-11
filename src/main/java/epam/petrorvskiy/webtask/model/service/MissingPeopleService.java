package epam.petrorvskiy.webtask.model.service;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MissingPeopleService {

    boolean addMissedPeople(MissingPeople people) throws ServiceException;
    boolean updateMissingPeopleById(MissingPeople missingPeople,long id) throws ServiceException;
    boolean deleteMissedHumanById(long humanId) throws ServiceException;
    List<MissingPeople> findAllMissingPeople() throws ServiceException;
    Optional<MissingPeople> takeMissedHumanById(MissingPeople people, long id) throws ServiceException;;
    List<MissingPeople> findAllMissingPeopleByName(String name) throws ServiceException;
}
