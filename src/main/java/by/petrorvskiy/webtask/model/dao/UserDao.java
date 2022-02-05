package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.entity.User.Role;
import by.petrorvskiy.webtask.entity.User.Status;
import by.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface UserDao {
    /**
     * @param email,password which used for searching
     * @return Optional User
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<User> findUserByEmailAndPassword(String email, String password)throws DaoException;

    /**
     * @param email which used for searching
     * @return Optional User
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<User> findUserByEmail(String email)throws DaoException;

    /**
     * @param userId which used for searching
     * @return Optional User
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<User> findUserById(long userId)throws DaoException;

    /**
     * @param userEmail which used for searching
     * @return Optional User
     * @throws DaoException if the request to data base could not be handled
     */
    Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException;

    /**
     * @param userName,userSurname with which {@link User}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User>findUsersByNameAndSurname(String userName,String userSurname) throws DaoException;

    /**
     * @param userName with which {@link User}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User>findUsersByName(String userName) throws DaoException;

    /**
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User>findAllUsers() throws DaoException;

    /**
     * @param userStatus with which {@link User}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User>findUsersByUserStatus(int userStatus)throws DaoException;

    /**
     * @param fromRow,numberOfUsersInPage with which {@link User}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage) throws DaoException;

    /**
     * @param userRole with which {@link User}s will be found
     * @return collection of {@link User}s
     * @throws DaoException if the request to data base could not be handled
     */
    List<User>findUsersByRole(Role userRole)throws DaoException;

    /**
     * @param userEmail
     * @return long if {@link User} founded by id
     * @throws DaoException if the request to data base could not be handled
     */
    long findUserIdByEmail(String userEmail) throws DaoException;

    /**
     * @param user,password
     * @return true if {@link User} added, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean addUser(User user, String password) throws DaoException;

    /**
     * @param userId,role
     * @return true if {@link User} role changed, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean changeUserRole(long userId,Role role) throws DaoException;

    /**
     * @param userId,status
     * @return true if {@link User} updated by id, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean updateUserStatusById(long userId,Status status) throws DaoException;

    /**
     * @param user
     * @return true if {@link User} info updated, otherwise false
     * @throws DaoException if the request to data base could not be handled
     */
    boolean updateUserInfo(User user) throws DaoException;

    /**
     * @return number of rows
     * @throws DaoException if the request to data base could not be handled
     */
    int findNumberOfRows() throws DaoException;


}
