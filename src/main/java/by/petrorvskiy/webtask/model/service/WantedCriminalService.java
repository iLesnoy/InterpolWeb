package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.DaoException;
import com.google.protobuf.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WantedCriminalService {

    boolean addWantedCriminal(WantedCriminal criminal, WantedCriminal.CrimType type) throws ServiceException;
    boolean deleteWantedCriminal(long criminalId) throws ServiceException;
    boolean updateWantedCriminalById(WantedCriminal wantedCriminal,long crimId) throws ServiceException;

    List<SearchApplication> findUserSearchApplicationsByUserId(long userId) throws ServiceException;
    List<WantedCriminal> findAllWantedCriminals() throws ServiceException;

    Optional<WantedCriminal> findAllCriminalsByName(String name) throws ServiceException;
    Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws ServiceException;
    Optional<BigDecimal> findCriminalRewardById(long criminalId) throws ServiceException;

}
