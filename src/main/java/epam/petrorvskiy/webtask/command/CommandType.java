package epam.petrorvskiy.webtask.command;

import epam.petrorvskiy.webtask.command.impl.*;
import epam.petrorvskiy.webtask.command.impl.find.FindNewsCommand;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByName;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByNameAndSurname;
import epam.petrorvskiy.webtask.command.impl.forward.ToAccountCommand;
import epam.petrorvskiy.webtask.command.impl.forward.ToMainCommand;
import epam.petrorvskiy.webtask.command.impl.forward.ToNewsFeedCommand;
import epam.petrorvskiy.webtask.command.impl.forward.ToSignInCommand;
import epam.petrorvskiy.webtask.command.impl.signin.LogOutCommand;
import epam.petrorvskiy.webtask.command.impl.signin.LoginCommand;
import epam.petrorvskiy.webtask.command.impl.signin.SignUpCommand;


public enum CommandType {
    LOG_IN(new LoginCommand()),
    LOG_OUT(new LogOutCommand()),
    REGISTER(new SignUpCommand()),
    DEFAULT(new DefaultCommand()),
    FIND_NEWS(new FindNewsCommand()),
    ADD_ARTICLE(new AddNewsCommand()),
    FIND_BY_NAME_AND_SURNAME(new FindUsersByNameAndSurname()),
    FIND_USERS_BY_NAME(new FindUsersByName()),
    TO_MAIN(new ToMainCommand()),
    TO_ACCOUNT(new ToAccountCommand()),
    TO_NEWS_ARTICLES(new ToNewsFeedCommand()),
    TO_SIGN_IN(new ToSignInCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
