package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface MissingPeopleDao{

    /**
     * @param people,stream which used for adding
     * @return true if {@link MissingPeople} added, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean addMissedPeople(MissingPeople people, InputStream stream) throws DaoException;

    /**
     * @param missingPeople,stream which used for updating
     * @return true if {@link MissingPeople} updated, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean updateMissingPeopleById(MissingPeople missingPeople, InputStream stream) throws DaoException;

    /**
     * @param humanId which used for deleting
     * @return true if {@link MissingPeople} deleted, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean deleteMissedHumanById(long humanId) throws DaoException;

    /**
     * @return collection of all {@link MissingPeople}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<MissingPeople> findAllMissingPeople() throws DaoException;

    /**
     * @param name with which {@link MissingPeople}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<MissingPeople> findAllMissingPeopleByName(String name) throws DaoException;

    /**
     * @param applicationId which used for searching
     * @return Optional User
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<MissingPeople> takeMissedHumanById(long applicationId) throws DaoException;
}
