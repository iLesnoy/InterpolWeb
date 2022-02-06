package by.petrorvskiy.webtask.model.dao.impl;

import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.dao.SearchApplicationDao;
import by.petrorvskiy.webtask.model.service.SearchApplicationService;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;

import static by.petrorvskiy.webtask.entity.SearchApplication.ApplicationStatus.ACTIVE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SearchApplicationDaoImplTest {
    private SearchApplicationDao dao;
    private SearchApplicationService service;
    private SearchApplication searchApplication;
    private SearchApplication searchApplication1;
    private Map<String, String> data ;
    List<SearchApplication> searchApplications = new ArrayList<>();


    @BeforeEach
    public  void setUp(){
        dao = mock(SearchApplicationDaoImpl.class);
        service = new SearchApplicationServiceImpl();
        data = new HashMap<>();
        data.put(ParameterAndAttribute.ARTICLE_ID, "72");
        data.put(ParameterAndAttribute.LEAD_TIME, "2022-03-04");
        data.put(ParameterAndAttribute.APPLICATION_STATUS, "ACTIVE");
        data.put(ParameterAndAttribute.USER_ID, "51");
        searchApplication = new SearchApplication.ApplicationBuilder()
                .setApplicationId(71).setLeadTime(LocalDate.parse("2022-03-04"))
                .setStatus(ACTIVE)
                .setUserId(51).build();
        searchApplication1 = new SearchApplication.ApplicationBuilder()
                .setApplicationId(78).setLeadTime(LocalDate.parse("1995-12-01"))
                .setStatus(SearchApplication.ApplicationStatus.PROCESS)
                .setUserId(60).build();
        searchApplications.add(searchApplication);
        searchApplications.add(searchApplication1);
    }


    @Test
    void addSearchApplication() throws DaoException, ServiceException {
        boolean expectedResult = true;
        when(dao.addSearchApplication(searchApplication)).thenReturn(true);
        boolean actualResult =service.addSearchApplication(searchApplication);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void updateSearchApplicationStatus() throws DaoException, ServiceException {
        boolean expectedResult = true;
        when(dao.updateSearchApplicationStatus(ACTIVE,78)).thenReturn(true);
        boolean actualResult =service.updateSearchApplicationStatus(ACTIVE,78);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void addWantedCriminalApplication() throws DaoException, ServiceException {
        boolean expectedResult = true;
        when(dao.addWantedCriminalApplication(72,10)).thenReturn(true);
        boolean actualResult =service.addWantedCriminalApplication(72,10);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void addMissingCriminalApplication() throws DaoException, ServiceException {
        boolean expectedResult = true;
        when(dao.addMissingCriminalApplication(73,25)).thenReturn(true);
        boolean actualResult =service.addMissingCriminalApplication(73,25);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void findAllSearchApplications() throws DaoException, ServiceException {
        List<SearchApplication> expectedResult = searchApplications;
        when(dao.findAllSearchApplications()).thenReturn(searchApplications);
        List<SearchApplication> actualResult = service.findAllSearchApplications();
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void deleteSearchApplicationByUserId() throws DaoException, ServiceException {
        boolean expectedResult = true;
        when(dao.deleteSearchApplicationByUserId(72,10)).thenReturn(true);
        boolean actualResult =service.deleteSearchApplicationByUserId(72,10);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void takeSearchApplicationById() throws ServiceException, DaoException {
        List<SearchApplication> expectedResult = searchApplications;
        when(dao.takeSearchApplicationById(72)).thenReturn(Optional.of(searchApplication));
        Optional<SearchApplication> actualResult = service.takeSearchApplicationById(72);
        Assertions.assertEquals(actualResult.get(), expectedResult);
    }

}