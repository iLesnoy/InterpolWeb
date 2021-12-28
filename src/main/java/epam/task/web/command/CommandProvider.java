package epam.task.web.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static epam.task.web.command.RequestParameter.*;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();

    private CommandProvider() {
    }

    public static Command defineCommand(HttpServletRequest request) {

        String command = request.getParameter(COMMAND);


        if (command == null || command.isEmpty()) {
            logger.info( "command is empty");
            return CommandType.DEFAULT.getCommand();
        }

        try {
            logger.info("in try CommandProvider");
            return CommandType.valueOf(command.toUpperCase()).getCommand();

        } catch (IllegalArgumentException exception) {
            logger.error( "command unknown command");
            return CommandType.DEFAULT.getCommand();
        }
    }
}
