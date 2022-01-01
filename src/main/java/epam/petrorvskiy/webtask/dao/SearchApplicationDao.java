package epam.petrorvskiy.webtask.dao;

import epam.petrorvskiy.webtask.entity.SearchApplication;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.Optional;

public interface SearchApplicationDao {

    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication status,long applicationId) throws DaoException;

    Optional<SearchApplication> deleteSearchApplicationByUserId(long userId) throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
}
