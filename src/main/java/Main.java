import epam.task.web.dao.impl.SearchApplicationDaoImpl;
import epam.task.web.entity.SearchApplication;
import epam.task.web.entity.SearchApplication.*;
import epam.task.web.exception.DaoException;

import java.sql.Date;


public class Main {

    public static void main(String[] args) throws DaoException {
        /*User user = new User("agent1@iterp.com","MEXICA","Heavier","Pennie");
        User user2 = new User("agent2@iterp.com","54goda","Melisa","Gonzales");
        User user3 = new User("Muhamed1978@Killer.com","private","Muhamed","Ali");
        User user4 = new User("Admin@gmail.com","admin","Admin","Admin",Role.ADMIN,Status.ACTIVE);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.addUser(user4);
        userDao.findUserPasswordByEmail("agent2@iterp.com");
        userDao.findUsersByNameAndSurname("Melisa","Gonzales");
        userDao.findUsersByRole(Role.USER);
        userDao.changeUserRole(2, User.Role.USER);*/

        /*NewsFeedDaoImpl newsFeedDao = new NewsFeedDaoImpl();
        NewsFeed newsFeed1 = new NewsFeed("missing child",2);
        newsFeedDao.takeArticleById(5);
        newsFeedDao.deleteArticlesByUserId(2);*/  /*newsFeedDAO*/

        /*WantedCriminalsDaoImpl wantedCriminalsDao = new WantedCriminalsDaoImpl();
        WantedCriminals criminals = new WantedCriminals("Hasbula","Mohamed","Tatarstan","Groza 32/2",new Date( 2001,21,8 ), new BigDecimal(900));
        wantedCriminalsDao.takeWantedCriminalById(2);*/

       /* MissingPeopleDaoImpl peopleDao = new MissingPeopleDaoImpl();
        Date date = new Date(20-02-2012);
        MissingPeople people = new MissingPeople("Valeriy","Albertovich",date);
        peopleDao.addMissedPeople(people);*/
        /*peopleDao.takeMissedHumanById(people,1);*/

        SearchApplication searchApplication = new SearchApplication(new Date(2021-04-31),8,ApplicationStatus.CONFIRMED);
        SearchApplicationDaoImpl applicationDao = new SearchApplicationDaoImpl();
        applicationDao.addSearchApplication(searchApplication);
        /*applicationDao.takeSearchApplicationById(1);*/


    }
}
