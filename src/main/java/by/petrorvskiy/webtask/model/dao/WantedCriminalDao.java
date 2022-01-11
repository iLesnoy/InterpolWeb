package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.entity.WantedCriminal.CrimType;
import by.petrorvskiy.webtask.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WantedCriminalDao {
    boolean addWantedCriminal(WantedCriminal criminal, CrimType type) throws DaoException;
    boolean deleteWantedCriminal(long criminalId) throws DaoException;
    boolean updateWantedCriminalById(WantedCriminal wantedCriminal,long crimId) throws DaoException;

    Optional<WantedCriminal> findAllCriminalsByName(String name) throws DaoException;
    List<SearchApplication> findUserSearchApplicationsByUserId(long userId) throws DaoException;

    Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws DaoException;
    Optional<BigDecimal> findCriminalRewardById(long criminalId) throws DaoException;

}
