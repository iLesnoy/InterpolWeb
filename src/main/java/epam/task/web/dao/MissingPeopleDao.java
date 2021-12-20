package epam.task.web.dao;

import epam.task.web.entity.MissingPeople;
import epam.task.web.exception.DaoException;

import java.util.Optional;

public interface MissingPeopleDao {

    boolean addMissedPeople(MissingPeople people) throws DaoException;
    Optional<MissingPeople> takeMissedHumanById(MissingPeople people,long id) throws DaoException;
}
