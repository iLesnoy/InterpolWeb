package epam.task.web.dao.impl;

import epam.task.web.connection.ConnectionPool;
import epam.task.web.dao.UserDao;
import epam.task.web.entity.User;
import epam.task.web.entity.User.Role;
import epam.task.web.entity.User.Status;
import epam.task.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static epam.task.web.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_USERS_BY_NAME_AND_SURNAME = "SELECT user_id,email,password,user_status,user_role FROM users WHERE name=? AND surname=?";
    private static final String SQL_FIND_USERS_BY_STATUS = "SELECT user_id,email,password,name,surname,user_role FROM users WHERE user_status=?";
    private static final String SQL_FIND_USERS_BY_ROLE = "SELECT user_id,email,password,name,surname,user_status FROM users WHERE user_role=?";
    private static final String SQL_FIND_USERS_BY_EMAIL_AND_PASSWORD = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE email=? AND password=?";
    private static final String SQL_FIND_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email=?";
    private static final String SQL_FIND_USER_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email=?";

    private static final String SQL_ADD_USER = "INSERT INTO users (email,password,name,surname,user_status,user_role) values(?,?,?,?,?,?)";
    private static final String SQL_CHANGE_ACCOUNT_STATUS_BY_ID = "UPDATE users SET user_status=? WHERE user_id=?";
    private static final String SQL_CHANGE_USER_ROLE_NY_ID = "UPDATE users SET user_role=? WHERE user_id=?";
    private static final String SQL_CHANGE_USER_STATUS = "UPDATE users SET user_status=? WHERE user_id=?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> optionalUser;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createUser(resultSet);
                optionalUser = Optional.of(user);
                logger.info("user=" + optionalUser);
            } else {
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUserByEmailAndPassword");
        }
        return optionalUser;
    }

    @Override
    public List<User> findUsersByNameAndSurname(String userName, String userSurname) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_NAME_AND_SURNAME)) {
            statement.setString(1, userName);
            statement.setString(2, userSurname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUsersByNameAndSurname", e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByUserStatus(int userStatus) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_STATUS)) {
            statement.setInt(1, userStatus);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("exception in method findUsersByUserStatus", e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByRole(Role userRole) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE)) {
            statement.setString(1, userRole.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("exception in method findUsersByRole", e);
        }
        return users;
    }

    @Override
    public Optional<User> findUserIdByEmail(String userEmail) throws DaoException {
        Optional<User> optionalUser;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ID_BY_EMAIL)) {
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createUser(resultSet);
                optionalUser = Optional.of(user);
            } else {
                logger.info("didn't find user with login:" + userEmail);
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("exception in method findUserByEmail", e);
        }
        return optionalUser;
    }


    @Override
    public Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException {
        Optional<String> optionalPassword;
        String password;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_PASSWORD_BY_EMAIL)) {
            logger.debug("in try block");
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(USER_PASSWORD);
                optionalPassword = Optional.of(password);
                logger.info("password=" + password);
            } else {
                optionalPassword = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQLException in method findPasswordByLogin " + e.getMessage());
            throw new DaoException("Dao epam.task.web.exception", e);
        }
        return optionalPassword;
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        boolean userAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, String.valueOf(user.getStatus()).toUpperCase(Locale.ROOT));
            statement.setString(6, String.valueOf(user.getRole()));
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                userAdded = true;
                logger.info("user added" + user);
            } else {
                logger.error("user was not added");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method addUser, when we try to add user:" + user, e);
        }
        return userAdded;
    }

    @Override
    public boolean updateUserStatusById(long userId, Status status) throws DaoException {
        boolean blockAccount = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_ACCOUNT_STATUS_BY_ID)) {
            statement.setString(1, status.name());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                blockAccount = true;
                logger.info("account by id-" + userId + "status changed");
            } else {
                logger.error("account by id- " + userId + " could not changed");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method blockAccount", e);
        }
        return blockAccount;
    }

    @Override
    public boolean changeUserRole(long userId, Role role) throws DaoException {
        boolean resultChangeStatus = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_USER_ROLE_NY_ID)) {
            statement.setString(1, role.name());
            statement.setLong(2, userId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                resultChangeStatus = true;
                logger.info("account id" + userId + "status has been changed to " + role);
            } else {
                logger.error("account " + userId + "status not changed");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method changeIsBlockedStatus", e);
        }
        return resultChangeStatus;
    }

    @Override
    public boolean changeUserStatus(long userId, Status status) throws DaoException {
        boolean resultChangeStatus = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_USER_STATUS)) {
            statement.setString(1, status.name());
            statement.setLong(2, userId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                resultChangeStatus = true;
                logger.info("account id" + userId + "status has been changed to " + status);
            } else {
                logger.error("account " + userId + "status not changed");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method changeUserStatus", e);
        }
        return resultChangeStatus;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(USER_ID);
        String email = resultSet.getString(USER_EMAIL);
        String password = resultSet.getString(USER_PASSWORD);
        String name = resultSet.getString(USER_NAME);
        String surname = resultSet.getString(USER_SURNAME);
        Role role = Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase(Locale.ROOT));
        Status status = Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase(Locale.ROOT));

        User user = new User.UserBuilder()
                .setUserid(userId)
                .setEmail(email)
                .setPassword(password)
                .setName(name)
                .setSurname(surname)
                .setRole(role)
                .setStatus(status)
                .build();
        return user;
    }
}
