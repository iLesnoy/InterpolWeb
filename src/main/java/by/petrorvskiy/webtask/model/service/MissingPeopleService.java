package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MissingPeopleService {

    boolean addMissedPeople(Map<String, String> missingPeopleDate, InputStream stream) throws ServiceException;
    boolean updateMissingPeopleById(Map<String,String> missingData,InputStream stream) throws ServiceException;
    boolean deleteMissedHumanById(long humanId) throws ServiceException;
    List<MissingPeople> findAllMissingPeople() throws ServiceException;
    Optional<MissingPeople> takeMissedHumanById(long id) throws ServiceException;;
    List<MissingPeople> findAllMissingPeopleByName(String name) throws ServiceException;
}
