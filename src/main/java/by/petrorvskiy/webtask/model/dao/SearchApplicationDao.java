package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SearchApplicationDao {

    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status,long applicationId) throws DaoException;
    boolean deleteSearchApplicationByUserId(long userId) throws DaoException;
    boolean payForApplication(long applicationId, BigDecimal reward) throws DaoException;

    List<SearchApplication> findAllSearchApplications() throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
    List<SearchApplication> findApplicationsByUserId(long userId) throws DaoException;

    long findWantedCriminalGuiltyId(long applicationId) throws DaoException;
    long findMissingPeopleId(long applicationId) throws DaoException;
}
