package epam.petrorvskiy.webtask.service.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.dao.ColumnName;
import epam.petrorvskiy.webtask.dao.UserDao;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.exception.DaoException;
import epam.petrorvskiy.webtask.validator.UserValidator;
import epam.petrorvskiy.webtask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import epam.petrorvskiy.webtask.util.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<User> findUsersByName(String userName) throws ServiceException {
        logger.debug("findUsersByName" + userName);
        List<User> users = new ArrayList<>();
        try {

            users = userDao.findUsersByName(userName);

        } catch (DaoException e) {
            logger.error("dao exception in method findUsersByName" + e);
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
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        logger.debug( "findUserByEmailAndPassword");
        Optional<User> optionalEmailAndPassword;
        if(UserValidator.isValidEmail(email) && UserValidator.isValidPassword(String.valueOf(password))){
            try{
                optionalEmailAndPassword = userDao.findUserByEmailAndPassword(email, password);

            } catch (DaoException e) {
                logger.error( "dao exception in method findUserByEmailAndPassword" + e);
                throw new ServiceException(e);
            }

        } else {
            optionalEmailAndPassword = Optional.empty();
        }
        return optionalEmailAndPassword;
    }

    @Override
    public boolean addUser(Map<String, String> userDate) throws ServiceException {
        logger.debug( "addUser(), userData:" + userDate);
        boolean userAdded = false;
        if (UserValidator.isValidPassword(userDate.get(ColumnName.USER_PASSWORD))
                /*&& UserValidator.isValidName(userDate.get(ParameterAndAttribute.USER_NAME))*/
                && UserValidator.isValidEmail(userDate.get(ParameterAndAttribute.USER_EMAIL))) {
            String encodedPassword = PasswordEncoder.encodePassword(userDate.get(ColumnName.USER_PASSWORD));

            User user = new User.UserBuilder()
                    .setEmail(userDate.get(ParameterAndAttribute.USER_EMAIL))
                    .setPassword(encodedPassword)
                    .setName(userDate.get(ParameterAndAttribute.USER_NAME))
                    .setSurname(userDate.get(ParameterAndAttribute.USER_SURNAME))
                    .setStatus(User.Status.ACTIVE)
                    .setRole(User.Role.USER)
                    .build();
            String uniqToken = PasswordEncoder.encodePassword(userDate.get(ColumnName.USER_EMAIL));
            /*String url = userDate.get(ParameterAndAttribute.URL) + COMMAND_CONFIRM + TOKEN + uniqToken + EMAIL
                    + user.get(ParameterAndAttribute.USER_EMAIL);
            String message = Message.WELCOM + url;*/
            try {
                userAdded = userDao.addUser(user, encodedPassword);
                /*MailSender.sendEmail(user.getEmail(), Message.ACCOUNT_CONFIRMATION, message);*/
            } catch (DaoException e) {
                logger.error("dao exception in method addUser" + e);
                throw new ServiceException(e);
            }
        } else {
            logger.error("User is not added");
            userAdded = false;
        }
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
