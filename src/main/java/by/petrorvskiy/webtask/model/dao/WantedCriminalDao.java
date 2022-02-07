package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.DaoException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface WantedCriminalDao {


    /**
     * @param  criminal,photoStream used for adding
     * @return true if {@link WantedCriminal} added, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean addWantedCriminal(WantedCriminal criminal, InputStream photoStream) throws DaoException;
    boolean deleteWantedCriminal(long criminalId) throws DaoException;
    boolean updateWantedCriminalById(WantedCriminal wantedCriminal,InputStream stream) throws DaoException;

    /**
     * @return collection of {@link WantedCriminal}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<WantedCriminal> findAllWantedCriminals() throws DaoException;

    /**
     * @param name,criminalId which used for searching
     * @return Optional WantedCriminal
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<WantedCriminal> findAllCriminalsByName(String name) throws DaoException;
    Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws DaoException;
    Optional<BigDecimal> findCriminalRewardById(long criminalId) throws DaoException;

}
