package epam.petrorvskiy.webtask.model.service;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> findUsersByNameAndSurname(String userName, String userSurname)throws ServiceException;

    List<User> findUsersByUserStatus(int userStatus) throws ServiceException;
    List<User> findUsersByRole(User.Role role) throws ServiceException;
    List<User>findUsersByName(String userName) throws ServiceException;

    Optional<User> findUserIdByEmail(String userEmail) throws ServiceException;
    Optional<User> findUserByEmail(String email)throws ServiceException;
    Optional<User> findUserPasswordByEmail(String userEmail) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    boolean addUser(Map<String, String> userData) throws ServiceException;
    boolean updateUserStatusById(long userId, User.Status status) throws ServiceException;
    boolean changeUserRole(long userId, User.Role role) throws ServiceException;
    boolean changeUserStatusToBlock(long userId) throws ServiceException;
}
