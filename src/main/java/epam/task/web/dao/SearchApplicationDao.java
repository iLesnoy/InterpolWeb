package epam.task.web.dao;

import epam.task.web.entity.SearchApplication;
import epam.task.web.exception.DaoException;

import java.util.Optional;

public interface SearchApplicationDao {

    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication status,long applicationId) throws DaoException;

    Optional<String> deleteSearchApplicationByUserId(long userId) throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
}
