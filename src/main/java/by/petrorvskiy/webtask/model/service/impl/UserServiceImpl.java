package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.UserDao;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import by.petrorvskiy.webtask.util.PasswordEncoder;
import by.petrorvskiy.webtask.validator.UserValidator;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        logger.debug("findUserByEmail, email:" + email);
        Optional<User> user;
        try {
            if (UserValidator.isValidEmail(email)) {
                user = userDao.findUserByEmail(email);
            } else {
                user = Optional.empty();
            }
        } catch (DaoException e) {
            logger.error( "dao exception in method findUserByEmail" + e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserPasswordByEmail(String userEmail) throws ServiceException {
        logger.debug( "findUserPasswordByEmail");
        Optional<User> optionalUser = Optional.empty();
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
        Optional<User> optionalUser;
        if(UserValidator.isValidEmail(email) && UserValidator.isValidPassword(String.valueOf(password))){
            String encodedPassword = PasswordEncoder.encodePassword(password);
            logger.debug("Encoded password: " + encodedPassword);

            try{
                Optional<String>optionalPasswordFromDb = userDao.findUserPasswordByEmail(email);
                if(optionalPasswordFromDb.isPresent()){
                    String passwordFromDb = optionalPasswordFromDb.get();
                    logger.debug( "passwordFromDB: " + passwordFromDb);

                    if(passwordFromDb.equals(encodedPassword)){
                        logger.info("passwords equals, authorization is successful for user: " + email);
                        optionalUser = userDao.findUserByEmail(email);
                    }else {
                        logger.info("passwords not equals");
                        optionalUser = Optional.empty();
                    }
                }else{
                    optionalUser = Optional.empty();
                }

            } catch (DaoException e) {
                logger.error( "dao exception in method findUserByEmailAndPassword" + e);
                throw new ServiceException(e);
            }

        } else {
            optionalUser = Optional.empty();
        }
        return optionalUser;
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
                    + user.get(ParameterAndAttribute.USER_EMAIL);                                           //////////CHECK....
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
        boolean updatedStatusById;
        try {
            updatedStatusById = userDao.updateUserStatusById(userId,status);
        } catch (DaoException e) {
            logger.error("dao exception in method updateUserStatusById" + e);
            throw new ServiceException(e);
        }
        return updatedStatusById;
    }

    @Override
    public boolean changeUserRole(long userId, User.Role role) throws ServiceException {
        boolean changeUserRole;
        try {
            changeUserRole = userDao.changeUserRole(userId,role);
        } catch (DaoException e) {
            logger.error("dao exception in method changeUserRole" + e);
            throw new ServiceException(e);
        }
        return changeUserRole;
    }

    @Override
    public boolean changeUserStatusToBlock(long userId) throws ServiceException {
        boolean changeUserStatus;
        try {
            changeUserStatus = userDao.changeUserStatusToBlock(userId);
        } catch (DaoException e) {
            logger.error("dao exception in method changeUserStatus" + e);
            throw new ServiceException(e);
        }
        return changeUserStatus;
    }
}