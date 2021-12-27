package epam.task.web.dao;

import epam.task.web.entity.WantedCriminal;
import epam.task.web.entity.WantedCriminal.CrimType;
import epam.task.web.exception.DaoException;

import java.util.Optional;

public interface WantedCriminalDao {
    boolean addWantedCriminal(WantedCriminal criminal, CrimType type) throws DaoException;
    Optional<String> takeWantedCriminalById(long criminalId) throws DaoException;
    Optional<WantedCriminal> findCriminalByNameAndSurname(String name,String surname) throws DaoException;
    Optional<String> findCriminalRewardById(long criminalId) throws DaoException;
}
