
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

        WantedCriminalDaoImpl wantedCriminalDao = new WantedCriminalDaoImpl();
        wantedCriminalDao.findAllCriminalsByName("Hasbula");
    }
}
