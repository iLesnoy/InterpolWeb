
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.dao.impl.*;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;


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
        List<SearchApplication> searchApplication = null;

        SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();
        try {
            searchApplication = applicationService.findApplicationsByUserId(51);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(searchApplication);


    }
}
