package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface SearchApplicationDao {


    /**
     * @param  application,status,applicationId,userId,reward,guiltyId,missingId
     * used for adding,deleting,updating
     * @return true if {@link SearchApplication} added,deleted,updated otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean addSearchApplication(SearchApplication application) throws DaoException;
    boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status,long applicationId) throws DaoException;
    boolean deleteSearchApplicationByUserId(long userId,long applicationId) throws DaoException;

    /**
     * not used yet
     * @param applicationId,reward
     * @throws DaoException
     */
    boolean payForApplication(long applicationId, BigDecimal reward) throws DaoException;
    boolean addWantedCriminalApplication(long applicationId,long guiltyId) throws DaoException;
    boolean addMissingCriminalApplication(long applicationId,long missingId) throws DaoException;

    /**
     * @param userId,applicationId, which used for searching
     * @return Optional SearchApplication
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<SearchApplication> findApplicationByUserIdAndWantedId(long userId) throws DaoException;
    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException;
    Optional<SearchApplication> findApplicationByUserIdAndMissingId(long userId) throws DaoException;
    Optional<Long> findApplicationIdByUserId(long userId) throws DaoException;


    /**
     * @param  userId with which {@link SearchApplication}s will be found
     * @return collection of {@link SearchApplication}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<SearchApplication> findApplicationsByUserId(long userId) throws DaoException;
    List<SearchApplication> findAllSearchApplications() throws DaoException;

    /**
     * @param applicationId used for searching
     * @return wanted criminal Id
     * @throws DaoException if the request to data base could not be handled
     */
    long findWantedCriminalGuiltyId(long applicationId) throws DaoException;

    /**
     * @param applicationId used for searching
     * @return wanted missing people Id
     * @throws DaoException if the request to data base could not be handled
     */
    long findMissingPeopleId(long applicationId) throws DaoException;
}
