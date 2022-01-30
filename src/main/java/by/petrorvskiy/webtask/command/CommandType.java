package by.petrorvskiy.webtask.command;

import by.petrorvskiy.webtask.command.impl.*;
import by.petrorvskiy.webtask.command.impl.add.AddMissingCommand;
import by.petrorvskiy.webtask.command.impl.add.AddNewsCommand;
import by.petrorvskiy.webtask.command.impl.add.AddWantedCommand;
import by.petrorvskiy.webtask.command.impl.common.ChangeLocaleCommand;
import by.petrorvskiy.webtask.command.impl.common.DeleteSearchApplicationByUserIdCommand;
import by.petrorvskiy.webtask.command.impl.find.*;
import by.petrorvskiy.webtask.command.impl.forward.*;
import by.petrorvskiy.webtask.command.impl.signin.LogOutCommand;
import by.petrorvskiy.webtask.command.impl.signin.LogInCommand;
import by.petrorvskiy.webtask.command.impl.signin.SignUpCommand;
import by.petrorvskiy.webtask.command.impl.update.*;


public enum CommandType {
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),
    /*TO_LOG_IN(new ToLogInCommand()),*/
    /*TO_SIGN_UP(new ToSignUp),*/
    SIGN_UP(new SignUpCommand()),

    DEFAULT(new DefaultCommand()),
    FIND_NEWS(new FindNewsCommand()),
    ADD_NEWS(new AddNewsCommand()),
    ADD_CRIMINAL(new AddWantedCommand()),
    ADD_MISSING(new AddMissingCommand()),
    TO_ADD(new ToAddCommand()),
    FIND_BY_NAME_AND_SURNAME(new FindUserByNameAndSurnameCommand()),
    FIND_USERS_BY_NAME(new FindUsersByNameCommand()),
    CHANGE_USER_INFO(new ChangeUserInfoCommand()),
    UPDATE_USER_ROLE(new UpdateUserRoleCommand()),
    UPDATE_APPLICATION_STATUS(new UpdateApplicationStatusCommand()),
    FIND_USERS_BY_NAME_PAGINATION(new FindUsersPaginationCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    ACCEPT_WANTED_APPLICATION(new AcceptWantedSearchApplicationByIdCommand()),
    ACCEPT_MISSING_APPLICATION(new AcceptMissingSearchApplicationByIdCommand()),
    DELETE_APPLICATION(new DeleteSearchApplicationByUserIdCommand()),
    TO_MAIN(new ToMainCommand()),
    TO_WANTED(new ToWantedCriminalsCommand()),
    TO_ACCOUNT(new ToAccountCommand()),
    TO_NEWS_FEED(new ToNewsFeedCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    TO_UPDATE_ARTICLE(new ToUpdateArticleCommand()),
    TO_UPDATE_MISSING(new ToUpdateMissingCommand()),
    TO_UPDATE_WANTED(new ToUpdateWantedCommand()),
    UPDATE_MISSING(new UpdateMissingCommand()),
    UPDATE_WANTED(new UpdateWantedCommand()),
    UPDATE_ARTICLE(new UpdateArticleCommand()),
    DELETE_ARTICLE(new DeleteArticleCommand()),
    DELETE_CRIMINAL(new DeleteWantedCommand()),
    DELETE_MISSING(new DeleteMissingCommand()),
    FIND_WANTED_CRIMINALS(new FindWantedCriminalByNameCommand()),
    TO_PERSONAL_INFO_CHANGE(new ToChangeUserInfoCommand()),
    CHANGE_PERSONAL_INFO(new ChangeUserInfoCommand()),
    FIND_ALL_APPLICATIONS(new FindAllApplicationsCommand()),
    FIND_SEARCH_APPLICATIONS_BY_USER_ID(new FindAllSearchApplicationByUserIdCommand()),
    FIND_APPLICATION_INFORMATION_BY_ID(new FindApplicationInformationByIdCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    TO_MISSING_PEOPLE(new ToMissingPeopleCommand());


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    
}
