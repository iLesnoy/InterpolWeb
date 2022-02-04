package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.DaoException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface WantedCriminalDao {

    boolean addWantedCriminal(WantedCriminal criminal, InputStream photoStream) throws DaoException;
    boolean deleteWantedCriminal(long criminalId) throws DaoException;
    boolean updateWantedCriminalById(WantedCriminal wantedCriminal,InputStream stream) throws DaoException;
    List<WantedCriminal> findAllWantedCriminals() throws DaoException;
    Optional<WantedCriminal> findAllCriminalsByName(String name) throws DaoException;
    Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws DaoException;
    Optional<BigDecimal> findCriminalRewardById(long criminalId) throws DaoException;

}
