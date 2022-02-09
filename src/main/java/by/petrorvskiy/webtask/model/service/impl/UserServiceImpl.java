package by.petrorvskiy.webtask.model.service.impl;

import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.UserDao;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import by.petrorvskiy.webtask.util.PasswordEncoder;
import by.petrorvskiy.webtask.validator.UserValidator;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Map;
import java.util.Optional;



public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final int numberOfUsersInPage = 10;


    @Override
    public List<User> findUsersByNameAndSurname(String userName, String userSurname) throws ServiceException {
        logger.debug("findUserByNameAndSurname" +userName);
        List<User> users;
        try {

            users = userDao.findUsersByNameAndSurname(userName,userSurname);

        } catch (DaoException e) {
            logger.error( "DaoException in method findUsersByNameAndSurname", e);
            throw new ServiceException("Service exception",e);
        }

        return users;
    }

    @Override
    public List<User> findUsersByUserStatus(int userStatus) throws ServiceException {
        logger.debug("findUsersByUserStatus" + userStatus);
        List<User> users;
        try {

            users = userDao.findUsersByUserStatus(userStatus);

        } catch (DaoException e) {
            logger.error("DaoException in method findUsersByUserStatus",e);
            throw new ServiceException("Service exception",e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByRole(User.Role userRole) throws ServiceException {
        logger.debug("findUsersByRole" + userRole);
        List<User> users;
        try {

            users = userDao.findUsersByRole(userRole);

        } catch (DaoException e) {
            logger.error("DaoException in method findUsersByRole", e);
            throw new ServiceException("Service exception",e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByName(String userName) throws ServiceException {
        logger.debug("findUsersByName" + userName);
        List<User> users;
        try {

            users = userDao.findUsersByName(userName);

        } catch (DaoException e) {
            logger.error("DaoException in method findUsersByName", e);
            throw new ServiceException("Service exception",e);
        }
        return users;
    }

    @Override
    public int findNumberOfPages() throws ServiceException {
        logger.debug( "FindNumberOfPages:");
        int numberOfPages;
        int numberOfUser;
        try {
            numberOfUser = userDao.findNumberOfRows();
            if (numberOfUser > numberOfUsersInPage) {
                numberOfPages = (int) Math.ceil((double) numberOfUser / numberOfUsersInPage);
            } else {
                numberOfPages = 1;
            }
        } catch (DaoException e) {
            logger.error( "DaoException in method findNumberOfRows",e);
            throw new ServiceException("Service exception",e);
        }
        return numberOfPages;
    }

    @Override
    public List<User> findUsersFromRow(int pageNumber) throws ServiceException {
        logger.debug( "findUsersFromRow, page number:" + pageNumber);
        List<User> users;
        int fromRow;
        if (pageNumber > 1) {
            fromRow = (pageNumber - 1) * numberOfUsersInPage;
        } else {
            fromRow = 0;
        }
        try {
            users = userDao.findUsersFromRow(fromRow, numberOfUsersInPage);
        } catch (DaoException e) {
            logger.error( "DaoException in method findUsersFromRow", e);
            throw new ServiceException("Service exception",e);
        }
        return users;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> foundedUsers;
        try {

            foundedUsers = userDao.findAllUsers();

        } catch (DaoException e) {
            logger.error("DaoException in method findUsersAllUser",e);
            throw new ServiceException("Service exception",e);
        }
        return foundedUsers;
    }

    @Override
    public Optional<User> findUserById(long userId) throws ServiceException {
        Optional<User>optionalUser;
        try {

            optionalUser = userDao.findUserById(userId);

        } catch (DaoException e) {
            logger.error("DaoException in method findUserById",e);
            throw new ServiceException("Service exception",e);
        }
        return optionalUser;
    }

    @Override
    public long findUserIdByEmail(String userEmail) throws ServiceException {
        logger.debug( "findUserIdByEmail, email:" + userEmail);
        long userId = 0;
        try {
            if (UserValidator.isValidEmail(userEmail)) {
                userId = userDao.findUserIdByEmail(userEmail);
            }

        } catch (DaoException e) {
            logger.error("DaoException in method findUserIdByEmail", e);
            throw new ServiceException("Service exception",e);
        }
        return userId;
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
            logger.error( "DaoException in method findUserByEmail", e);
            throw new ServiceException("Service exception",e);
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
                logger.error( "DaoException in method findUserPasswordByEmail", e);
                throw new ServiceException("Service exception",e);
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
                logger.error( "DaoException in method findUserByEmailAndPassword",e);
                throw new ServiceException("Service exception",e);
            }
        } else {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }

    @Override
    public boolean addUser(Map<String, String> userDate) throws ServiceException {
        logger.debug( "addUser(), userData:" + userDate);
        boolean userAdded;
        if (UserValidator.isValidPassword(userDate.get(ColumnName.USER_PASSWORD))
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
            try {
                userAdded = userDao.addUser(user, encodedPassword);
            } catch (DaoException e) {
                logger.error("DaoException in method addUser", e);
                throw new ServiceException("Service exception",e);
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
            logger.error("DaoException in method updateUserStatusById", e);
            throw new ServiceException("Service exception",e);
        }
        return updatedStatusById;
    }

    @Override
    public boolean changeUserRole(long userId, User.Role role) throws ServiceException {
        boolean changeUserRole;
        try {
            changeUserRole = userDao.changeUserRole(userId,role);
        } catch (DaoException e) {
            logger.error("DaoException in method changeUserRole", e);
            throw new ServiceException("Service exception",e);
        }
        return changeUserRole;
    }

    @Override
    public boolean updateUserInfo(User user, Map<String, String> userData) throws ServiceException {
        boolean isChanged = false;
        if (UserValidator.isValidName(userData.get(ParameterAndAttribute.USER_NAME))) {
            user.setName(userData.get(ParameterAndAttribute.USER_NAME));
            user.setSurname(userData.get(ParameterAndAttribute.USER_SURNAME));

            try {
                isChanged = userDao.updateUserInfo(user);
            } catch (DaoException e) {
                logger.error( "DaoException in method updateUserInfo", e);
                throw new ServiceException("Service exception",e);
            }
        }
        return isChanged;
    }

}
