package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.SearchApplication;
import com.google.protobuf.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SearchApplicationService {

    boolean addSearchApplication(SearchApplication application) throws ServiceException;
    boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status,long applicationId) throws ServiceException;
    boolean payForApplication(long applicationId, BigDecimal reward) throws ServiceException;
    boolean deleteSearchApplicationByUserId(long userId,long applicationId) throws ServiceException;
    boolean addWantedCriminalApplication(long applicationId,long guiltyId) throws ServiceException;
    boolean addMissingCriminalApplication(long applicationId,long missingId) throws ServiceException;


    List<SearchApplication> findAllSearchApplications() throws ServiceException;
    List<SearchApplication> findApplicationsByUserId(long userId) throws ServiceException;

    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws ServiceException;
    Optional<SearchApplication> findApplicationByUserIdAndWantedId(long applicationId,long guiltyId) throws ServiceException;
    Optional<SearchApplication> findApplicationByUserIdAndMissingId(long applicationId,long missingId) throws ServiceException;
    long findWantedCriminalGuiltyId(long applicationId) throws ServiceException;
    Optional<Long> findApplicationIdByUserId(long userId) throws ServiceException;
    long findMissingPeopleId(long applicationId) throws ServiceException;
}
