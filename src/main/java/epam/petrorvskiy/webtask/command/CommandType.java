package epam.petrorvskiy.webtask.command;

import epam.petrorvskiy.webtask.command.impl.*;
import epam.petrorvskiy.webtask.command.impl.find.FindNewsCommand;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByName;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByNameAndSurname;


public enum CommandType {
    LOG_IN(new LoginCommand()),
    LOG_OUT(new LogOutCommand()),
    REGISTER(new RegistrationCommand()),
    DEFAULT(new DefaultCommand()),
    NEWS_FEED(new FindNewsCommand()),
    ADD_ARTICLE(new AddNewsCommand()),
    FIND_BY_NAME_AND_SURNAME(new FindUsersByNameAndSurname()),
    FIND_USERS_BY_NAME(new FindUsersByName()),
    TO_MAIN(new ToMainCommand()),
    TO_ACCOUNT(new ToAccountCommand()),
    TO_SIGN_IN(new ToSignInCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
