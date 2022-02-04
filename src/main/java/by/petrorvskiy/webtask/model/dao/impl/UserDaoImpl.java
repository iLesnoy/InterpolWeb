package by.petrorvskiy.webtask.model.dao.impl;

import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.connection.ConnectionPool;
import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.UserDao;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.entity.User.Role;
import by.petrorvskiy.webtask.entity.User.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_USERS_BY_NAME_AND_SURNAME = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE name=? AND surname=?";
    private static final String SQL_FIND_USERS_BY_STATUS = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE user_status=?";
    private static final String SQL_FIND_USERS_BY_ROLE = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE user_role=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE user_id=?";
    private static final String SQL_FIND_USERS_BY_EMAIL_AND_PASSWORD = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE email=? AND password=?";
    private static final String SQL_FIND_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email=?";
    private static final String SQL_FIND_USER_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT user_id,email,password,name,surname,user_status,user_role password FROM users";
    private static final String SQL_FIND_USERS_BY_EMAIL ="SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE email=?";
    private static final String SQL_FIND_USERS_BY_NAME = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users WHERE name =? ";
    private static final String SQL_FIND_USERS_FROM_ROW = "SELECT user_id,email,password,name,surname,user_status,user_role FROM users LIMIT ?,?";
    private static final String SQL_ADD_USER = "INSERT INTO users (email,password,name,surname,user_status,user_role) values(?,?,?,?,?,?)";
    private static final String SQL_CHANGE_ACCOUNT_STATUS_BY_ID = "UPDATE users SET user_status=? WHERE user_id=?";
    private static final String SQL_CHANGE_USER_ROLE_BY_ID = "UPDATE users SET user_role=? WHERE user_id=?";
    private static final String SQL_UPDATE_USER_INFO = "UPDATE users SET name=?,surname=? WHERE user_id=? ";
    private static final String SQL_COUNT_ALL_USERS = "SELECT COUNT(*) FROM users";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    public UserDaoImpl(){
    }

    public static UserDaoImpl getInstance(){
        return INSTANCE;
    }

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
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createUser(resultSet);
                optionalUser = Optional.of(user);
                logger.info("user=" + optionalUser);
            } else {
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUserByEmail");
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserById(long userId) throws DaoException {
        Optional<User> optionalUser;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createUser(resultSet);
                optionalUser = Optional.of(user);
                logger.info("user=" + optionalUser);
            } else {
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUserById");
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
            if (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUsersByNameAndSurname", e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByName(String userName) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_NAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUsersByName", e);
        }
        return users;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findUsersAllUser", e);
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
            throw new DaoException("Dao exception in method findUsersByUserStatus", e);
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
            throw new DaoException("Dao exception in method findUsersByRole", e);
        }
        return users;
    }

    @Override
    public long findUserIdByEmail(String userEmail) throws DaoException {
        long userId = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ID_BY_EMAIL)) {
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getLong(1);
                logger.info("founded user Id by email " +userId );
            } 
            
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method findUserIdByEmail", e);
        }
        return userId;
    }


    @Override
    public Optional<String> findUserPasswordByEmail(String userEmail) throws DaoException {
        Optional<String> optionalPassword;
        String password;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_PASSWORD_BY_EMAIL)) {
            logger.debug("in try block findUserPasswordByEmail");
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(ColumnName.USER_PASSWORD);
                optionalPassword = Optional.of(password);
                logger.info("password=" + password);
            } else {
                optionalPassword = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQLException in method findPasswordByLogin " + e.getMessage());
            throw new DaoException("Dao exception", e);
        }
        return optionalPassword;
    }

    @Override
    public boolean addUser(User user, String password) throws DaoException {
        boolean userAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, password);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, String.valueOf(user.getStatus()));
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
            throw new DaoException("Dao exception in method addUser, when we try to add user:" + user, e);
        }
        return userAdded;
    }

    @Override
    public boolean updateUserStatusById(long userId, Status status) throws DaoException {
        boolean updateStatus = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_ACCOUNT_STATUS_BY_ID)) {
            statement.setString(1, status.name());
            statement.setLong(2, userId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                updateStatus = true;
                logger.info("upd user account by id-" + userId + "status changed to " + status);
            } else {
                logger.error("upd user account by id- " + userId + " could not changed");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method updateUserStatusById", e);
        }
        return updateStatus;
    }

    @Override
    public boolean updateUserInfo(User user) throws DaoException {
        boolean updateUser = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_INFO)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setLong(3, user.getUserId());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                updateUser = true;
                logger.info("userInfo updated");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method updateUserInfo", e);
        }
        return updateUser;
    }

    @Override
    public List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage) throws DaoException {
        logger.info( "findUsersFromRow");
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_FROM_ROW)) {
            statement.setInt(1, fromRow);
            statement.setInt(2, numberOfUsersInPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            logger.error( "SQLException in findUsersFromRow(): " + e.getMessage() + " : " + e.getErrorCode());
            throw new DaoException("Dao exception", e);
        }
        return users;
    }

    @Override
    public int findNumberOfRows() throws DaoException {
        logger.info( "findNumberOfRows");
        int numberOfRows = 0;
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_COUNT_ALL_USERS)) {
            if (resultSet.next()) {
                numberOfRows = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error( "SQLException in findNumberOfRows: " + e.getMessage() + " : " + e.getErrorCode());
            throw new DaoException("Dao exception", e);
        }
        return numberOfRows;
    }

    @Override
    public boolean changeUserRole(long userId, Role role) throws DaoException {
        boolean resultChangeStatus = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_USER_ROLE_BY_ID)) {
            statement.setString(1, role.name());
            statement.setLong(2, userId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                resultChangeStatus = true;
                logger.info("changeUserRole by id " + userId + "status has been changed to " + role);
            } else {
                logger.error("changeUserRole by id" + userId + "status not changed");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method changeUserRole", e);
        }
        return resultChangeStatus;
    }



    private User createUser(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ColumnName.USER_ID);
        String email = resultSet.getString(ColumnName.USER_EMAIL);
        String password = resultSet.getString(ColumnName.USER_PASSWORD);
        String name = resultSet.getString(ColumnName.USER_NAME);
        String surname = resultSet.getString(ColumnName.USER_SURNAME);
        Role role = Role.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase());
        Status status = Status.valueOf(resultSet.getString(ColumnName.USER_STATUS).toUpperCase());

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
