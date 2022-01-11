package epam.petrorvskiy.webtask.model.dao;

import epam.petrorvskiy.webtask.entity.SearchApplication;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SearchApplicationDao {

    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication status,long applicationId) throws DaoException;
    boolean payForApplication(long applicationId, BigDecimal reward) throws DaoException;

    List<SearchApplication> findAllSearchApplications() throws DaoException;

    Optional<SearchApplication> deleteSearchApplicationByUserId(long userId) throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
}
