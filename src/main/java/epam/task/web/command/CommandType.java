package epam.task.web.command;

import epam.task.web.command.impl.DefaultCommand;
import epam.task.web.command.impl.LoginCommand;


public enum CommandType {
    LOG_IN(new LoginCommand()),
    DEFAULT(new DefaultCommand());
    /*TO_MAIN(new ToMainCommand());*/

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
