package epam.task.web.dao;

import epam.task.web.entity.User;
import epam.task.web.entity.User.Role;
import epam.task.web.entity.User.Status;
import epam.task.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByEmailAndPassword(String email, String password)throws DaoException;
    List<User>findUsersByNameAndSurname(String userName,String userSurname) throws DaoException;
    List<User>findUsersByUserStatus(int userStatus)throws DaoException;
    List<User>findUsersByRole(Role userRole)throws DaoException;

    Optional<User> findUserIdByEmail(String userEmail) throws DaoException;
    Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException;

    boolean addUser(User user) throws DaoException;
    boolean updateUserStatusById(long userId,Status status) throws DaoException;
    boolean changeUserRole(long userId,Role role) throws DaoException;
    boolean changeUserStatus(long userId,Status status) throws DaoException;


}
