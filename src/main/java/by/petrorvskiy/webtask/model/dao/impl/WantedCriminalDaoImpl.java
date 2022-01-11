package by.petrorvskiy.webtask.model.dao.impl;

import by.petrorvskiy.webtask.model.dao.ColumnName;
import by.petrorvskiy.webtask.model.dao.WantedCriminalDao;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.model.connection.ConnectionPool;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.entity.WantedCriminal.CrimType;
import by.petrorvskiy.webtask.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WantedCriminalDaoImpl implements WantedCriminalDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_TAKE_CRIMINAL_BY_ID = "SELECT first_name,last_name,crim_city,crim_address,crim_DOB,reward,crime_type FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_FIND_CRIMINAL_BY_NAME = "SELECT guilty_id,first_name,last_name,crim_city,crim_address,crim_DOB,reward,crime_type FROM wanted_criminals WHERE first_name=?";
    private static final String SQL_FIND_CRIMINAL_REWARD_BY_ID = "SELECT reward FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_DELETE_CRIMINAL_BY_ID = "DELETE FROM wanted_criminals WHERE guilty_id =?";
    private static final String SQL_UPDATE_WANTED_CRIMINAL_BY_ID = "UPDATE wanted_criminals SET first_name=?,last_name=?,crim_city=?,crim_address=?,crim_DOB=?,reward=?,crime_type=? WHERE guilty_id=? ";

    private static final String SQL_ADD_CRIMINAL = "INSERT INTO wanted_criminals (first_name,last_name,crim_city,crim_address,crim_DOB,reward,crime_type) values(?,?,?,?,?,?,?)";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addWantedCriminal(WantedCriminal criminal, CrimType type) throws DaoException {
        boolean articleAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_CRIMINAL)) {
            statement.setString(1, criminal.getFirstName());
            statement.setString(2, criminal.getLastName());
            statement.setString(3, criminal.getCrimCity());
            statement.setString(4, criminal.getCrimAddress());
            statement.setDate(5, Date.valueOf(criminal.getCrimDOB()));
            statement.setBigDecimal(6, criminal.getReward());
            statement.setString(7, type.name());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleAdded = true;
                logger.info("criminal added" + criminal);
            } else {
                logger.error("criminal was not added");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method addWantedCriminal, when we try to add user:" + criminal, e);
        }
        return articleAdded;
    }

    @Override
    public boolean deleteWantedCriminal(long criminalId) throws DaoException {
        boolean deleteCriminal = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CRIMINAL_BY_ID)) {
            statement.setLong(1, criminalId);

            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                deleteCriminal = true;
                logger.info("criminal deleted by id " + criminalId);
            } else {
                logger.error("criminal was not deleted");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method deleteWantedCriminal");
        }
        return deleteCriminal;
    }

    @Override
    public boolean updateWantedCriminalById(WantedCriminal wantedCriminal, long crimId) throws DaoException {
        boolean wantedCriminalUpdate = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_WANTED_CRIMINAL_BY_ID)) {
            statement.setString(1, wantedCriminal.getFirstName());
            statement.setString(2, wantedCriminal.getLastName());
            statement.setString(3, wantedCriminal.getCrimCity());
            statement.setString(4, wantedCriminal.getCrimAddress());
            statement.setDate(5, Date.valueOf(wantedCriminal.getCrimDOB()));
            statement.setBigDecimal(6, wantedCriminal.getReward());
            statement.setString(7, wantedCriminal.getCrimeType().name());
            statement.setLong(8, crimId);

            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                wantedCriminalUpdate = true;
                logger.info("criminal updated" + wantedCriminalUpdate);
            } else {
                logger.error("criminal not updated");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method updateWantedCriminalById", e);
        }
        return wantedCriminalUpdate;
    }

    @Override
    public Optional<WantedCriminal> takeWantedCriminalById(long criminalId) throws DaoException {
        Optional<WantedCriminal> optionalCriminal;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TAKE_CRIMINAL_BY_ID)) {
            logger.debug("in try block");
            statement.setLong(1, criminalId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                WantedCriminal wantedCriminal = createCriminal(resultSet);
                optionalCriminal = Optional.of(wantedCriminal);
                logger.info("criminal=" + optionalCriminal);
            } else {
                optionalCriminal = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQLException in method takeWantedCriminalById " + e.getMessage());
            throw new DaoException("Dao exception in method takeWantedCriminalById, when we try to take Criminal by Id", e);
        }
        return optionalCriminal;
    }

    @Override
    public Optional<WantedCriminal> findAllCriminalsByName(String name) throws DaoException {
        Optional<WantedCriminal> optionalCriminals;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_CRIMINAL_BY_NAME)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                WantedCriminal wantedCriminal = createCriminal(resultSet);
                optionalCriminals = Optional.of(wantedCriminal);
                logger.info("criminal=" + optionalCriminals);
            } else {
                optionalCriminals = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQLException in method findCriminalByName " + e.getMessage());
            throw new DaoException("Dao exception in method findCriminalByName, when we try to take Criminal by Name", e);
        }
        return optionalCriminals;
    }

    @Override
    public List<SearchApplication> findUserSearchApplicationsByUserId(long userId) throws DaoException {
        return null;
    }

    @Override
    public Optional<BigDecimal> findCriminalRewardById(long criminalId) throws DaoException {
        Optional<BigDecimal> optionalReward;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_CRIMINAL_REWARD_BY_ID)) {

            statement.setLong(1,criminalId);
            ResultSet resultSet = statement.executeQuery();


            if(resultSet.next()){
                BigDecimal reward = resultSet.getBigDecimal(ColumnName.REWARD);
                optionalReward = Optional.of(reward);
                logger.info("founded reward " + reward + " by crimId " + criminalId);
            }else {
                optionalReward = Optional.empty();
            }

        } catch (SQLException e) {
            logger.error("SQLException in method findCriminalRewardById " + e.getMessage());
            throw new DaoException("Dao exception in method findCriminalRewardById", e);
        }
        return optionalReward;
    }

        private WantedCriminal createCriminal (ResultSet resultSet) throws SQLException {
            long guiltyId = resultSet.getLong(ColumnName.GUILTY_ID);
            String firstName = resultSet.getString(ColumnName.FIRST_NAME);
            String lastName = resultSet.getString(ColumnName.LAST_NAME);
            String crimCity = resultSet.getString(ColumnName.CRIM_CITY);
            String address = resultSet.getString(ColumnName.ADDRESS);
            LocalDate DOB = resultSet.getDate(ColumnName.CRIM_DOB).toLocalDate();
            BigDecimal reward = resultSet.getBigDecimal(ColumnName.REWARD);
            CrimType crimeType = CrimType.valueOf(resultSet.getString(ColumnName.CRIM_TYPE));
            WantedCriminal wantedCriminal = new WantedCriminal.WantedCriminalBuilder()
                    .setGuiltyId(guiltyId)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setCrimCity(crimCity)
                    .setCrimAdress(address)
                    .setDOB(DOB)
                    .setReward(reward)
                    .setCrimType(crimeType).build();
            logger.info("criminalCreated " + wantedCriminal);

            return wantedCriminal;
        }
    }
