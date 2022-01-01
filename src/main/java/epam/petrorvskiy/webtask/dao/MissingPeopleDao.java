package epam.petrorvskiy.webtask.dao;

import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.Optional;

public interface MissingPeopleDao {

    boolean addMissedPeople(MissingPeople people) throws DaoException;
    Optional<MissingPeople> takeMissedHumanById(MissingPeople people,long id) throws DaoException;
}
