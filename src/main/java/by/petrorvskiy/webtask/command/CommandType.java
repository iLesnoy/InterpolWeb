package by.petrorvskiy.webtask.command;

import by.petrorvskiy.webtask.command.impl.*;
import by.petrorvskiy.webtask.command.impl.find.*;
import by.petrorvskiy.webtask.command.impl.forward.*;
import by.petrorvskiy.webtask.command.impl.signin.LogOutCommand;
import by.petrorvskiy.webtask.command.impl.signin.LogInCommand;
import by.petrorvskiy.webtask.command.impl.signin.SignUpCommand;


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
    CHANGE_USER_INFO(new ChangeUserInfoCommand()),
    CHANGE_USER_ROLE_TO_USER(new ChangeUserRoleToUserCommand()),
    CHANGE_USER_ROLE_TO_AGENT(new ChangeUserRoleToAgentCommand()),
    CHANGE_USER_ROLE_TO_ADMIN(new ChangeUserRoleToAdminCommand()),
    UPDATE_APPLICATION_STATUS_TO_ACTIVE(new ChangeApplicationStatusToActiveCommand()),
    UPDATE_APPLICATION_STATUS_TO_CLOSED(new ChangeApplicationStatusToClosedCommand()),
    UPDATE_APPLICATION_STATUS_TO_REJECTED(new ChangeApplicationStatusToRejectedCommand()),
    UPDATE_APPLICATION_STATUS_TO_EXPIRED(new ChangeApplicationStatusToExpiredCommand()),
    FIND_USERS_BY_NAME_PAGINATION(new FindUsersPaginationCommand()),
    FIND_ALL_USERS(new FindAllUserCommand()),

    TO_MAIN(new ToMainCommand()),
    TO_ACCOUNT(new ToAccountCommand()),
    TO_NEWS_FEED(new ToNewsFeedCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    FIND_WANTED_CRIMINALS(new FindWantedCriminalByNameCommand()),
    TO_PERSONAL_INFO_CHANGE(new ToChangeUserInfoCommand()),
    CHANGE_PERSONAL_INFO(new ChangeUserInfoCommand()),
    FIND_ALL_APPLICATIONS(new FindAllApplicationsCommand()),
    FIND_SEARCH_APPLICATIONS_BY_USER_ID(new FindSearchApplicationsByUserIdCommand()),

    TO_MISSING(new ToMissingPeopleCommand());


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
