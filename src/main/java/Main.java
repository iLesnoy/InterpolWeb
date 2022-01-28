
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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws DaoException, ServiceException {

        Random random = new Random();
        random.nextInt(100);
        List<Long> longs = random.longs(5).boxed().collect(Collectors.toList());
        System.out.println(longs);


    }
}
