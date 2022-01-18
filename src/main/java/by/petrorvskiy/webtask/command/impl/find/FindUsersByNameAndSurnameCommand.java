package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindUsersByNameAndSurnameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

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
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
