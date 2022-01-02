package epam.petrorvskiy.webtask.command.impl.find;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.command.impl.Message;
import epam.petrorvskiy.webtask.controller.Router;
import epam.petrorvskiy.webtask.dao.impl.UserDaoImpl;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.service.UserService;
import epam.petrorvskiy.webtask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindUsersByNameAndSurname implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        Router router = new Router();
        logger.debug( "find user by name: " + name + " surname: " + surname);
        List<User> users;
        try{
            users = userService.findUsersByNameAndSurname(name,surname);
            router.setPagePath(page);

            if(users.size()>0){
                request.setAttribute(ParameterAndAttribute.LIST,users);
            }else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        }catch (ServiceException e) {
            logger.error("UserServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
