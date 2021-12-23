package epam.task.web.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static epam.task.web.command.RequestParameter.COMMAND;
import static epam.task.web.command.RequestParameter.EMAIL_PARAMETER;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public static Command defineCommand(HttpServletRequest request) {

        String command = request.getParameter(COMMAND);


        // command - пусто || Не работает с этого момента
        if (command == null || command.isEmpty()) {
            logger.info( "command is empty");
            return CommandType.DEFAULT.getCommand();
        }

        try {
            return CommandType.valueOf(command.toUpperCase()).getCommand();

        } catch (IllegalArgumentException exception) {
            logger.error( "command is empty");
            return CommandType.DEFAULT.getCommand();
        }
    }
}