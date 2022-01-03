package epam.petrorvskiy.webtask.model.dao;

import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.entity.User.Role;
import epam.petrorvskiy.webtask.entity.User.Status;
import epam.petrorvskiy.webtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByEmailAndPassword(String email, String password)throws DaoException;
    List<User>findUsersByNameAndSurname(String userName,String userSurname) throws DaoException;
    List<User>findUsersByName(String userName) throws DaoException;
    List<User>findUsersByUserStatus(int userStatus)throws DaoException;
    List<User>findUsersByRole(Role userRole)throws DaoException;

    Optional<User> findUserIdByEmail(String userEmail) throws DaoException;
    Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;
    boolean updateUserStatusById(long userId,Status status) throws DaoException;
    boolean changeUserRole(long userId,Role role) throws DaoException;
    boolean changeUserStatus(long userId,Status status) throws DaoException;


}
