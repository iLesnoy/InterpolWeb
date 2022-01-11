package epam.petrorvskiy.webtask.command.impl.find;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.command.Message;
import epam.petrorvskiy.webtask.command.Router;
import epam.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.model.service.UserService;
import epam.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static epam.petrorvskiy.webtask.command.ParameterAndAttribute.LIST;

public class FindUsersByName implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        String userName = request.getParameter(ParameterAndAttribute.USER_NAME);

        logger.debug( "find user by name: " + userName);
        List<User> users;
        try{

            users = userService.findUsersByName(userName);
            router.setPagePath(page);

            if(users.size() > 0){

                request.setAttribute(ParameterAndAttribute.LIST,users);
                logger.debug(request.getAttribute(LIST));

            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        }catch (ServiceException e) {
            logger.error("UserServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
