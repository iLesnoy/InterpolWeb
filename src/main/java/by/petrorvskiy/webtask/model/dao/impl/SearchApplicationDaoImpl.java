package by.petrorvskiy.webtask.model.dao.impl;

import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.connection.ConnectionPool;
import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.SearchApplicationDao;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.SearchApplication.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchApplicationDaoImpl implements SearchApplicationDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ADD_SEARCH_APPLICATION = "INSERT INTO search_application (lead_time,status,users_user_id) values (?,?,?)";
    private static final String SQL_UPDATE_SEARCH_APPLICATION_STATUS_BY_ID = "UPDATE search_application SET status=?  WHERE search_application_id= ?";
    private static final String SQL_DELETE_SEARCH_APPLICATION_BY_ID = "DELETE FROM search_application WHERE  search_application_id=?";
    private static final String SQL_TAKE_SEARCH_APPLICATION_BY_ID = "SELECT search_application_id,lead_time,status,users_user_id FROM search_application  WHERE search_application_id =?";
    private static final String SQL_TAKE_SEARCH_APPLICATION_BY_USER_ID = "SELECT search_application_id,lead_time,status,users_user_id WHERE users_user_id=?";
    private static final String SQL_TAKE_ALL_SEARCH_APPLICATION = "SELECT search_application_id,lead_time,status,users_user_id FROM search_application";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean addSearchApplication(SearchApplication application) throws DaoException {
        boolean applicationAdd = false;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_SEARCH_APPLICATION)) {
            statement.setDate(1, Date.valueOf(application.getLeadTime()));
            statement.setString(2, String.valueOf(application.getStatus()));
            statement.setLong(3, application.getUserId());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                applicationAdd = true;
                logger.info( "application added" + application);
            } else {
                logger.error( "application was not added");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method addSearchApplication, when we try to add application:" + application, e);
        }
        return applicationAdd;
    }

    @Override
    public boolean updateSearchApplicationStatus(SearchApplication status,long applicationId) throws DaoException {
        boolean updateSearchApplication = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SEARCH_APPLICATION_STATUS_BY_ID)) {
            statement.setString(1, String.valueOf(status.getStatus()));
            statement.setLong(1, applicationId);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                updateSearchApplication = true;
                logger.info("application by id-" + applicationId + " status changed"  );
            } else {
                logger.error( "application by id-" + applicationId + " not changed");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method updateSearchApplicationStatus", e);
        }
        return updateSearchApplication;
    }

    @Override
    public boolean payForApplication(long applicationId, BigDecimal reward) throws DaoException {
        return false;
    }

    @Override
    public List<SearchApplication> findAllSearchApplications() throws DaoException {
        List<SearchApplication> searchApplications = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TAKE_ALL_SEARCH_APPLICATION)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                searchApplications.add(createApplication(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findAllSearchApplications", e);
        }
        return searchApplications;
    }

    @Override
    public Optional<SearchApplication> deleteSearchApplicationByUserId(long userId) throws DaoException {
        Optional<SearchApplication> deletedSearchApplication;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SEARCH_APPLICATION_BY_ID)) {
            logger.debug( "in try block");
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SearchApplication deletedApplication = createApplication(resultSet);
                deletedSearchApplication = Optional.of(deletedApplication);
                logger.info( "deleted application id =" + deletedApplication);
            } else {
                deletedSearchApplication = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQLException in method deleteSearchApplicationByUserId " + e.getMessage());
            throw new DaoException("Dao epam.task.web.exception in method deleteSearchApplicationByUserId", e);
        }
        return deletedSearchApplication;
    }

    @Override
    public Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws DaoException {
        Optional<SearchApplication> searchApplication;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TAKE_SEARCH_APPLICATION_BY_ID)) {
            logger.debug( "in try block");
            statement.setLong(1, applicationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SearchApplication application = createApplication(resultSet);
                searchApplication = Optional.of(application);
                logger.info( "application =" + application);
            } else {
                searchApplication = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQLException in method takeSearchApplicationById " + e.getMessage());
            throw new DaoException("Dao exception", e);
        }
        return searchApplication;
    }

    private SearchApplication createApplication(ResultSet resultSet) throws SQLException {
        long applicationId = resultSet.getLong(ColumnName.SEARCH_APPLICATION_ID);
        LocalDate leadTime = resultSet.getDate(ColumnName.LEAD_TIME).toLocalDate();
        ApplicationStatus status = ApplicationStatus.valueOf(resultSet.getString(ColumnName.APPLICATION_STATUS));
        long userId = resultSet.getLong(ColumnName.USERS_USER_ID);

        SearchApplication searchApplication = new SearchApplication.ApplicationBuilder()
                .setApplicationId(applicationId)
                .setLeadTime(leadTime)
                .setStatus(status)
                .setUserId(userId)
                .build();
        logger.info("application " + searchApplication);
        return searchApplication;
    }
}
