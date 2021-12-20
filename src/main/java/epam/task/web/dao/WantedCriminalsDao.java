package epam.task.web.dao;

import epam.task.web.entity.WantedCriminals;
import epam.task.web.entity.WantedCriminals.CrimType;
import epam.task.web.exception.DaoException;

import java.util.Optional;

public interface WantedCriminalsDao {
    boolean addWantedCriminal(WantedCriminals criminal,CrimType type) throws DaoException;
    Optional<String> takeWantedCriminalById(long criminalId) throws DaoException;
}
