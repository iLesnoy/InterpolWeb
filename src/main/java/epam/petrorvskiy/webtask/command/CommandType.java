package epam.petrorvskiy.webtask.command;

import epam.petrorvskiy.webtask.command.impl.*;
import epam.petrorvskiy.webtask.command.impl.find.FindNewsCommand;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByName;
import epam.petrorvskiy.webtask.command.impl.find.FindUsersByNameAndSurname;
import epam.petrorvskiy.webtask.command.impl.find.FindWantedCriminalsByName;
import epam.petrorvskiy.webtask.command.impl.forward.*;
import epam.petrorvskiy.webtask.command.impl.signin.LogOutCommand;
import epam.petrorvskiy.webtask.command.impl.signin.LogInCommand;
import epam.petrorvskiy.webtask.command.impl.signin.SignUpCommand;


public enum CommandType {
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),
    /*TO_LOG_IN(new ToLogInCommand()),*/
    /*TO_SIGN_UP(new ToSignUp),*/
    SIGN_UP(new SignUpCommand()),

    DEFAULT(new DefaultCommand()),
    FIND_NEWS(new FindNewsCommand()),
    ADD_ARTICLE(new AddNewsCommand()),
    FIND_BY_NAME_AND_SURNAME(new FindUsersByNameAndSurname()),
    FIND_USERS_BY_NAME(new FindUsersByName()),
    CHANGE_USER_ROLE_TO_ADMIN(new ChangeUserRoleAdminCommand()),

    TO_MAIN(new ToMainCommand()),
    TO_ACCOUNT(new ToAccountCommand()),
    TO_NEWS_FEED(new ToNewsFeedCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    FIND_WANTED_CRIMINALS(new FindWantedCriminalsByName()),
    TO_MISSING(new ToMissingPeopleCommand());


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
