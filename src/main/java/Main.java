
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import by.petrorvskiy.webtask.model.dao.impl.WantedCriminalDaoImpl;
import by.petrorvskiy.webtask.exception.DaoException;


public class Main {

    public static void main(String[] args) throws DaoException {
        /*LocalDate date = LocalDate.of(1960,4,3);
        SearchApplication searchApplication = new SearchApplication.ApplicationBuilder()
                .setUserId(1).setStatus(SearchApplication.ApplicationStatus.CLOSED)
                                .setLeadTime(date).build();
        WantedCriminal wantedCriminal= new WantedCriminal.WantedCriminalBuilder().setFirstName("Asbar")
                .setLastName("Lisko").setCrimCity("Los").setCrimAdress("Asbiorn 21/2").setDOB(date)
                .setReward(new BigDecimal(500)).setCrimType(WantedCriminal.CrimType.Murder).build();

        MissingPeopleDaoImpl missingPeopleDao = new MissingPeopleDaoImpl();
        missingPeopleDao.findAllMissingPeople();*/

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = new User.UserBuilder().setEmail("vor@vor.by").setPassword("12345")
                        .setName("Muhamad").setSurname("Dorn").setStatus(User.Status.BLOCKED).setRole(User.Role.USER).build();
    }
}
