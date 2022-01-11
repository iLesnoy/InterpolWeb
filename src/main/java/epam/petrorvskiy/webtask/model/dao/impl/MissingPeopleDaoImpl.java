package epam.petrorvskiy.webtask.model.dao.impl;

import epam.petrorvskiy.webtask.model.connection.ConnectionPool;
import epam.petrorvskiy.webtask.model.dao.MissingPeopleDao;
import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static epam.petrorvskiy.webtask.model.dao.ColumnName.*;

public class MissingPeopleDaoImpl implements MissingPeopleDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ADD_MISSING_PEOPLE = "INSERT INTO missing_people (first_name,last_name,disappearance_date) values (?,?,?)";
    private static final String SQL_MISSING_PEOPLE_SEARCH_BY_ID = "SELECT missing_people_id,first_name,last_name,disappearance_date FROM missing_people WHERE missing_people_id =? ";
    private static final String SQL_UPDATE_MISSING_PEOPLE = "UPDATE missing_people SET first_name=?,last_name=?,disappearance_date=? WHERE missing_people_id=?";
    private static final String SQL_FIND_ALL_MISSING_PEOPLE ="SELECT missing_people_id,first_name,last_name,disappearance_date FROM missing_people";
    private static final String SQL_DELETE_HUMAN_BY_ID = "DELETE FROM missing_people WHERE missing_people_id=?";
    private static final String SQL_FIND_ALL_MISSING_PEOPLE_BY_NAME = "SELECT missing_people_id,first_name,last_name,disappearance_date FROM missing_people WHERE first_name =? ";


    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean addMissedPeople(MissingPeople people) throws DaoException {
        boolean missingPeopleAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_MISSING_PEOPLE)) {
            statement.setString(1, people.getName());
            statement.setString(2, people.getSurname());
            statement.setDate(3, people.getDisappearanceDate());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                missingPeopleAdded = true;
                logger.info("people added" + people);
            } else {
                logger.error("people was not added");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method addMissedPeople, when we try to add user:" + people, e);
        }
        return missingPeopleAdded;
    }

    @Override
    public boolean updateMissingPeopleById(MissingPeople missingPeople, long id) throws DaoException {
        boolean missingPeopleUpdate = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MISSING_PEOPLE)) {
            statement.setString(1, missingPeople.getName());
            statement.setString(2, missingPeople.getSurname());
            statement.setDate(3, missingPeople.getDisappearanceDate());
            statement.setLong(4, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                missingPeopleUpdate = true;
                logger.info("people updated" + missingPeople);
            } else {
                logger.error("people not updated");
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method updateMissingPeopleById" + missingPeople, e);
        }
        return missingPeopleUpdate;
    }

    @Override
    public boolean deleteMissedHumanById(long humanId) throws DaoException {
        boolean deleteHuman;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_HUMAN_BY_ID)) {
            statement.setLong(1, humanId);
            statement.executeUpdate();
            deleteHuman = true;

        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method deleteMissedHumanById, when we try to delete human by id:" + humanId, e);
        }
        return deleteHuman;
    }

    @Override
    public List<MissingPeople> findAllMissingPeople() throws DaoException {
        List<MissingPeople> findAllMissingPeople = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_MISSING_PEOPLE)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                findAllMissingPeople.add(createMissingPeople(resultSet));
            }

        } catch (SQLException e) {
            logger.error("SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
            throw new DaoException("Dao exception in method findAllMissingPeople", e);
        }
        return findAllMissingPeople;
    }


    @Override
    public Optional<MissingPeople> takeMissedHumanById(MissingPeople people, long id) throws DaoException {
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

    @Override
    public List<MissingPeople> findAllMissingPeopleByName(String name) throws DaoException {
        List<MissingPeople> missingPeople = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_MISSING_PEOPLE_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                missingPeople.add(createMissingPeople(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Dao exception in method findAllMissingPeopleByName", e);
        }
        return missingPeople;
    }

    private MissingPeople createMissingPeople(ResultSet resultSet) throws SQLException {
        Long peopleId = resultSet.getLong(MISSING_PEOPLE_ID);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        Date disappearanceDate = resultSet.getDate(DISAPPEARANCE_DATE);
        MissingPeople people = new MissingPeople.MissingPeopleBuilder()
                .setPeopleId(peopleId)
                .setName(firstName)
                .setSurname(lastName)
                .setDisappearanceDate(disappearanceDate)
                .build();
        logger.info("created human " + people);
        return people;

    }
}
