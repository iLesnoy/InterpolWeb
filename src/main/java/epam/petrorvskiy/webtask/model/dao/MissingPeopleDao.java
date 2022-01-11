package epam.petrorvskiy.webtask.model.dao;

import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MissingPeopleDao {

    boolean addMissedPeople(MissingPeople people) throws DaoException;
    boolean updateMissingPeopleById(MissingPeople missingPeople,long id) throws DaoException;
    boolean deleteMissedHumanById(long humanId) throws DaoException;
    List<MissingPeople> findAllMissingPeople() throws DaoException;
    Optional<MissingPeople> takeMissedHumanById(MissingPeople people,long id) throws DaoException;
    List<MissingPeople> findAllMissingPeopleByName(String name) throws DaoException;
}
