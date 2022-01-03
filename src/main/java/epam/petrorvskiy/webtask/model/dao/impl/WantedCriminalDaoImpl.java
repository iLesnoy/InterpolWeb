package epam.petrorvskiy.webtask.model.dao.impl;

import epam.petrorvskiy.webtask.model.dao.ColumnName;
import epam.petrorvskiy.webtask.model.dao.WantedCriminalDao;
import epam.petrorvskiy.webtask.model.connection.ConnectionPool;
import epam.petrorvskiy.webtask.entity.WantedCriminal;
import epam.petrorvskiy.webtask.entity.WantedCriminal.CrimType;
import epam.petrorvskiy.webtask.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class WantedCriminalDaoImpl implements WantedCriminalDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_TAKE_CRIMINAL_BY_ID = "SELECT first_name,last_name,crim_city,crim_adress,crim_DOB,reward,crime_type FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_FIND_CRIMINAL_BY_FIRST_NAME_AND_LAST_SURNAME = "SELECT guilty_id,first_name,last_name,crim_city,crim_adress,crim_DOB,reward,crime_type FROM wanted_criminals WHERE first_name=? AND last_name=?";
    private static final String SQL_FIND_CRIMINAL_REWARD_BY_ID = "SELECT reward FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_DELETE_CRIMINAL_BY_ID = "DELETE FROM wanted_criminals WHERE guilty_id =?";

    private static final String SQL_ADD_CRIMINAL = "INSERT INTO wanted_criminals (first_name,last_name,crim_city,crim_adress,crim_DOB,reward,crime_type) values(?,?,?,?,?,?,?)";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addWantedCriminal(WantedCriminal criminal, CrimType type) throws DaoException {
        boolean articleAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_CRIMINAL)) {
            statement.setString(1, criminal.getFirstName());
            statement.setString(2, criminal.getLastName());
            statement.setString(3, criminal.getCrimCity());
            statement.setString(4, criminal.getCrimAdress());
            statement.setDate(5, (Date) criminal.getCrimDOB());
            statement.setBigDecimal(6, criminal.getReward());
            statement.setString(7, type.name());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                articleAdded = true;
                logger.info( "criminal added" + criminal);
            } else {
                logger.error( "criminal was not added");
            }
        } catch (SQLException e) {
            logger.error( "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method addWantedCriminal, when we try to add user:" + criminal, e);
        }
        return articleAdded;
    }

    @Override
    public Optional<String> takeWantedCriminalById(long criminalId) throws DaoException {
        Optional<String> optionalCriminal;
        String criminal;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_TAKE_CRIMINAL_BY_ID)) {
            logger.debug( "in try block");
            statement.setLong(1, criminalId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                criminal = resultSet.getString(ColumnName.CRIMINAL_FIRST_NAME);
                optionalCriminal = Optional.of(criminal);
                logger.info( "criminal=" + criminal);
            } else {
                optionalCriminal = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQLException in method takeWantedCriminalById " + e.getMessage());
            throw new DaoException("Dao exception in method takeWantedCriminalById, when we try to take Criminal by Id", e);
        }
        return optionalCriminal;
    }

    @Override
    public Optional<WantedCriminal> findCriminalByNameAndSurname(String name,String surname) throws DaoException {
        Optional<WantedCriminal> optionalCriminal;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_CRIMINAL_BY_FIRST_NAME_AND_LAST_SURNAME)) {
            logger.debug( "in try block");

            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                WantedCriminal wantedCriminal = createCriminal(resultSet);
                optionalCriminal = Optional.of(wantedCriminal);
                logger.info( "criminal=" + optionalCriminal);
            } else {
                optionalCriminal = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQLException in method findCriminalByNameAndSurname " + e.getMessage());
            throw new DaoException("Dao exception in method findCriminalByNameAndSurname, when we try to take Criminal by Name and Surname", e);
        }
        return optionalCriminal;
    }

    @Override
    public Optional<String> findCriminalRewardById(long criminalId) throws DaoException {
        return Optional.empty();
    }

    private WantedCriminal createCriminal(ResultSet resultSet) throws SQLException {
        long guiltyId = resultSet.getLong(ColumnName.GUILTY_ID);
        String firstName = resultSet.getString(ColumnName.FIRST_NAME);
        String lastName = resultSet.getString(ColumnName.LAST_NAME);
        String crimCity = resultSet.getString(ColumnName.CRIM_CITY);
        String adress = resultSet.getString(ColumnName.ADRESS);
        Date DOB = resultSet.getDate(ColumnName.CRIM_DOB);
        BigDecimal reward = resultSet.getBigDecimal(ColumnName.REWARD);
        CrimType crimeType = CrimType.valueOf(resultSet.getString(ColumnName.CRIM_TYPE));
        WantedCriminal wantedCriminal = new WantedCriminal.WantedCriminalBuilder()
                .setGuiltyId(guiltyId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setCrimCity(crimCity)
                .setCrimAdress(adress)
                .setDOB(DOB)
                .setReward(reward)
                .setCrimType(crimeType).build();

        return wantedCriminal;
    }
}
