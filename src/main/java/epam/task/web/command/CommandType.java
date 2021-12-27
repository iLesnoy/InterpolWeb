package epam.task.web.command;

import epam.task.web.command.impl.AddNewsCommand;
import epam.task.web.command.impl.DefaultCommand;
import epam.task.web.command.impl.FindNewsCommand;
import epam.task.web.command.impl.LoginCommand;


public enum CommandType {
    LOG_IN(new LoginCommand()),
    DEFAULT(new DefaultCommand()),
    NEWS_FEED(new FindNewsCommand()),
    ADD_ARTICLE(new AddNewsCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
