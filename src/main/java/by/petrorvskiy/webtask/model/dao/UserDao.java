package by.petrorvskiy.webtask.model.dao;

import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.entity.User.Role;
import by.petrorvskiy.webtask.entity.User.Status;
import by.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByEmailAndPassword(String email, String password)throws DaoException;
    Optional<User> findUserByEmail(String email)throws DaoException;
    List<User>findUsersByNameAndSurname(String userName,String userSurname) throws DaoException;
    List<User>findUsersByName(String userName) throws DaoException;
    List<User>findAllUsers() throws DaoException;
    List<User>findUsersByUserStatus(int userStatus)throws DaoException;
    List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage) throws DaoException;
    List<User>findUsersByRole(Role userRole)throws DaoException;

    Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException;
    long findUserIdByEmail(String userEmail) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;
    boolean changeUserRole(long userId,Role role) throws DaoException;
    boolean updateUserStatusById(long userId,Status status) throws DaoException;
    boolean updateUserInfo(User user) throws DaoException;

    int findNumberOfRows() throws DaoException;


}
