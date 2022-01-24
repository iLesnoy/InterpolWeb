
import by.petrorvskiy.webtask.command.impl.find.FindUserByNameAndSurnameCommand;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.DaoException;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import com.google.protobuf.ServiceException;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws DaoException, ServiceException {
        UserDaoImpl userDao =new UserDaoImpl();
        userDao.findUsersByNameAndSurname("Heavier","Pennie");


    }
}
