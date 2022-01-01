package epam.petrorvskiy.webtask.dao.impl;

import epam.petrorvskiy.webtask.connection.ConnectionPool;
import epam.petrorvskiy.webtask.dao.MissingPeopleDao;
import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

import static epam.petrorvskiy.webtask.dao.ColumnName.*;

public class MissingPeopleDaoImpl implements MissingPeopleDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_MISSING_PEOPLE_SEARCH_BY_ID = "SELECT missing_people_id,first_name,last_name,disappearance_date FROM missing_people WHERE missing_people_id =? ";
    private static final String SQL_ADD_MISSING_PEOPLE = "INSERT INTO missing_people (first_name,last_name,disappearance_date) values (?,?,?)";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addMissedPeople(MissingPeople people) throws DaoException {
        boolean missingPeopleAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_MISSING_PEOPLE)) {
            statement.setString(1, people.getName());
            statement.setString(2, people.getSurname());
            statement.setDate(3,  people.getDisappearanceDate());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                missingPeopleAdded = true;
                logger.info("people added" + people);
            } else {
                logger.error("people was not added");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method addMissedPeople, when we try to add user:" + people, e);
        }
        return missingPeopleAdded;
    }


    @Override
    public Optional<MissingPeople> takeMissedHumanById(MissingPeople people,long id) throws DaoException {
        Optional<MissingPeople> missingPeople;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_MISSING_PEOPLE_SEARCH_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MissingPeople missedPeople = createMissingPeople(resultSet);
                missingPeople = Optional.of(missedPeople);
                logger.info("takenHumanById " + missedPeople);
            } else {
                logger.info("article was not found");
                missingPeople = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao epam.task.web.exception in method takeArticleById, when we try to take article:" + people, e);
        }
        return missingPeople;
    }

    private MissingPeople createMissingPeople(ResultSet resultSet) throws SQLException {
        Long peopleId = resultSet.getLong(MISSING_PEOPLE_ID);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        Date disappearanceDate = resultSet.getDate(DISAPPEARANCE_DATE);
        MissingPeople people = new MissingPeople(peopleId,firstName,lastName,disappearanceDate);
        return people;

    }
}
