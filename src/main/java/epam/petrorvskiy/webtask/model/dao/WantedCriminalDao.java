package epam.petrorvskiy.webtask.model.dao;

import epam.petrorvskiy.webtask.entity.WantedCriminal;
import epam.petrorvskiy.webtask.entity.WantedCriminal.CrimType;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.Optional;

public interface WantedCriminalDao {
    boolean addWantedCriminal(WantedCriminal criminal, CrimType type) throws DaoException;
    Optional<String> takeWantedCriminalById(long criminalId) throws DaoException;
    Optional<WantedCriminal> findCriminalByNameAndSurname(String name,String surname) throws DaoException;
    Optional<String> findCriminalRewardById(long criminalId) throws DaoException;
}
