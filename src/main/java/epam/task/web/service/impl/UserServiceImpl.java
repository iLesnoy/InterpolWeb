package epam.task.web.service.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.dao.UserDao;
import epam.task.web.entity.User;
import epam.task.web.exception.DaoException;
import epam.task.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import epam.task.web.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findUsersByNameAndSurname(String userName, String userSurname) throws ServiceException {
        logger.debug("findUserByNameAndSurname()" +userName);
        List<User> users = new ArrayList<>();
        try {

            users = userDao.findUsersByNameAndSurname(userName,userSurname);

        } catch (DaoException e) {
            logger.error( "dao exception in method findUsersByNameAndSurname" + e);
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<User> findUsersByUserStatus(int userStatus) throws ServiceException {
        logger.debug("findUsersByUserStatus" + userStatus);
        List<User> users = new ArrayList<>();
        try {

            users = userDao.findUsersByUserStatus(userStatus);

        } catch (DaoException e) {
            logger.error("dao exception in method findUsersByUserStatus" + e);
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> findUsersByRole(User.Role userRole) throws ServiceException {
        logger.debug("findUsersByRole" + userRole);
        List<User> users = new ArrayList<>();
        try {

            users = userDao.findUsersByRole(userRole);

        } catch (DaoException e) {
            logger.error("dao exception in method findUsersByRole" + e);
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public Optional<User> findUserIdByEmail(String userEmail) throws ServiceException {
        logger.debug( "findUserIdByEmail, email:" + userEmail);
        Optional<User> user;
        try {
            if (UserValidator.isValidEmail(userEmail)) {
                user = userDao.findUserIdByEmail(userEmail);
            } else {
                user = Optional.empty();
            }
        } catch (DaoException e) {
            logger.error("dao exception in method findUserIdByEmail" + e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserPasswordByEmail(String userEmail) throws ServiceException {
        logger.debug( "findUserPasswordByEmail");
        Optional<User> optionalUser = null;
        if (UserValidator.isValidEmail(userEmail)) {

            try {
                Optional<String> password = userDao.findUserPasswordByEmail(userEmail);
                if (password.isPresent()) {
                    logger.debug( "passwordByEmail: " + password);
                } else {
                    optionalUser = Optional.empty();
                }
            } catch (DaoException e) {
                logger.error( "dao exception in method findUserPasswordByEmail" + e);
                throw new ServiceException(e);
            }
        } else {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        logger.debug( "addUser(), userData:" + user);
        boolean userAdded = false;

        return userAdded;
    }

    @Override
    public boolean updateUserStatusById(long userId, User.Status status) throws ServiceException {
        return false;
    }

    @Override
    public boolean changeUserRole(long userId, User.Role role) throws ServiceException {
        return false;
    }

    @Override
    public boolean changeUserStatus(long userId, User.Status status) throws ServiceException {
        return false;
    }
}
