package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;
import com.google.protobuf.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SearchApplicationDao {

    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status,long applicationId) throws DaoException;
    boolean deleteSearchApplicationByUserId(long userId,long applicationId) throws DaoException;
    boolean payForApplication(long applicationId, BigDecimal reward) throws DaoException;
    boolean addWantedCriminalApplication(long applicationId,long guiltyId) throws DaoException;
    boolean addMissingCriminalApplication(long applicationId,long missingId) throws DaoException;

    Optional<SearchApplication> findApplicationByUserIdAndWantedId(long applicationId,long guiltyId) throws DaoException;
    Optional<SearchApplication> findApplicationByUserIdAndMissingId(long applicationId,long missingId) throws DaoException;


    List<SearchApplication> findAllSearchApplications() throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
    List<SearchApplication> findApplicationsByUserId(long userId) throws DaoException;

    long findWantedCriminalGuiltyId(long applicationId) throws DaoException;
    Optional<Long> findApplicationIdByUserId(long userId) throws DaoException;
    long findMissingPeopleId(long applicationId) throws DaoException;
}
