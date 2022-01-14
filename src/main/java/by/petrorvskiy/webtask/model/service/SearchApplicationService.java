package by.petrorvskiy.webtask.model.service;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;
import com.google.protobuf.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SearchApplicationService {

    boolean addSearchApplication(SearchApplication application) throws ServiceException;
    boolean updateSearchApplicationStatus(SearchApplication.ApplicationStatus status,long applicationId) throws ServiceException;
    boolean payForApplication(long applicationId, BigDecimal reward) throws ServiceException;
    boolean deleteSearchApplicationByUserId(long userId) throws ServiceException;

    List<SearchApplication> findAllSearchApplications() throws ServiceException;

    Optional<SearchApplication> takeSearchApplicationById(long applicationId) throws ServiceException;
    Optional<SearchApplication> findApplicationsByUserId(long userId) throws ServiceException;
}
