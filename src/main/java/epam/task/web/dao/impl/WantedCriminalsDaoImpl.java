package epam.task.web.dao.impl;

import epam.task.web.connection.ConnectionPool;
import epam.task.web.dao.WantedCriminalsDao;
import epam.task.web.entity.WantedCriminals;
import epam.task.web.entity.WantedCriminals.CrimType;
import epam.task.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

import static epam.task.web.dao.ColumnName.CRIMINAL_FIRST_NAME;

public class WantedCriminalsDaoImpl implements WantedCriminalsDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_TAKE_CRIMINAL_BY_ID = "SELECT first_name,last_name,crim_city,crim_adress,crim_DOB,reward,crime_type FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_FIND_CRIMINAL_BY_NAME_AND_SURNAME = "SELECT guilty_id,crim_DOB,reward,crime_type FROM wanted_criminals WHERE first_name=? AND last_name=?";
    private static final String SQL_FIND_CRIMINAL_REWARD_BY_ID = "SELECT reward FROM wanted_criminals WHERE guilty_id=?";
    private static final String SQL_DELETE_CRIMINAL_BY_ID = "DELETE FROM wanted_criminals WHERE guilty_id =?";

    private static final String SQL_ADD_CRIMINAL = "INSERT INTO wanted_criminals (first_name,last_name,crim_city,crim_adress,crim_DOB,reward,crime_type) values(?,?,?,?,?,?,?)";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addWantedCriminal(WantedCriminals criminal,CrimType type) throws DaoException {
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
            throw new DaoException("Dao epam.task.web.exception in method addWantedCriminal, when we try to add user:" + criminal, e);
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
                criminal = resultSet.getString(CRIMINAL_FIRST_NAME);
                optionalCriminal = Optional.of(criminal);
                logger.info( "criminal=" + criminal);
            } else {
                optionalCriminal = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error( "SQLException in method findPasswordByLogin " + e.getMessage());
            throw new DaoException("Dao epam.task.web.exception", e);
        }
        return optionalCriminal;
    }
}
