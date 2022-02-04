package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.ServiceException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WantedCriminalService {

    boolean addWantedCriminal(Map<String,String> wantedData, InputStream photoStream) throws ServiceException;
    boolean deleteWantedCriminal(long criminalId) throws ServiceException;
    boolean updateWantedCriminalById(Map<String,String> wantedData, InputStream photoStream) throws ServiceException;

    List<WantedCriminal> findAllWantedCriminals() throws ServiceException;

    Optional<WantedCriminal> findAllCriminalsByName(String name) throws ServiceException;
    Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws ServiceException;
    Optional<BigDecimal> findCriminalRewardById(long criminalId) throws ServiceException;

}
