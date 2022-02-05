package by.petrorvskiy.webtask.command;

import by.petrorvskiy.webtask.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The {@link CommandProvider} class
 * used by the {@link Controller} to identify the incoming command
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();

    private CommandProvider() {
    }

    public static Command defineCommand(HttpServletRequest request) {
        String command = request.getParameter(ParameterAndAttribute.COMMAND);


        if (command == null) {
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
