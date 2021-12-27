import epam.task.web.dao.impl.SearchApplicationDaoImpl;
import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.dao.impl.WantedCriminalDaoImpl;
import epam.task.web.entity.SearchApplication;
import epam.task.web.entity.SearchApplication.*;
import epam.task.web.entity.WantedCriminal;
import epam.task.web.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLData;


public class Main {

    public static void main(String[] args) throws DaoException {
       /* User user = new User("agent1@iterp.com","MEXICA","Heavier","Pennie");
        User user2 = new User("agent2@iterp.com","54goda","Melisa","Gonzales");
        User user3 = new User("Muhamed1978@Killer.com","private","Muhamed","Ali");
        User user4 = new User("Admin@gmail.com","admin","Admin","Admin",Role.ADMIN,Status.ACTIVE);*/
        /*UserDaoImpl userDao = new UserDaoImpl();
        userDao.findUserByEmailAndPassword("agent1@iterp.com","MEXICA");*/

        /*NewsFeedDaoImpl newsFeedDao = new NewsFeedDaoImpl();
        NewsFeed newsFeed1 = new NewsFeed("missing child",2);
        newsFeedDao.takeArticleById(5);
        newsFeedDao.deleteArticlesByUserId(2);*/  /*newsFeedDAO*/

        WantedCriminalDaoImpl wantedCriminalsDao = new WantedCriminalDaoImpl();
        WantedCriminal criminals = new WantedCriminal("Hasbula","Mohamed","Tatarstan","Groza 32/2", new Date(2001, 21, 8), new BigDecimal(900), WantedCriminal.CrimType.Murder);
        /*wantedCriminalsDao.takeWantedCriminalById(2);*/
/*
        wantedCriminalsDao.addWantedCriminal(criminals, WantedCriminal.CrimType.Murder);


       /* MissingPeopleDaoImpl peopleDao = new MissingPeopleDaoImpl();
        Date date = new Date(20-02-2012);
        MissingPeople people = new MissingPeople("Valeriy","Albertovich",date);
        peopleDao.addMissedPeople(people);*/
        /*peopleDao.takeMissedHumanById(people,1);*/

        /*SearchApplication searchApplication = new SearchApplication(new Date(2021-04-31),8,ApplicationStatus.CONFIRMED);
        SearchApplicationDaoImpl applicationDao = new SearchApplicationDaoImpl();
        applicationDao.addSearchApplication(searchApplication);*/
        /*applicationDao.takeSearchApplicationById(1);*/
        wantedCriminalsDao.findCriminalByNameAndSurname("Hasbula","Mohamed");
        wantedCriminalsDao.takeWantedCriminalById(7);

    }
}
