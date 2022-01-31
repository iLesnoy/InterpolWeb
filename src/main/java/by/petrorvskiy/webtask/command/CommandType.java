package by.petrorvskiy.webtask.command;

import by.petrorvskiy.webtask.command.impl.admin.*;
import by.petrorvskiy.webtask.command.impl.admin.add.AddMissingCommand;
import by.petrorvskiy.webtask.command.impl.admin.add.AddNewsCommand;
import by.petrorvskiy.webtask.command.impl.admin.add.AddWantedCommand;
import by.petrorvskiy.webtask.command.impl.admin.find.*;
import by.petrorvskiy.webtask.command.impl.admin.go.ToAddCommand;
import by.petrorvskiy.webtask.command.impl.admin.go.ToUpdateArticleCommand;
import by.petrorvskiy.webtask.command.impl.admin.go.ToUpdateMissingCommand;
import by.petrorvskiy.webtask.command.impl.admin.go.ToUpdateWantedCommand;
import by.petrorvskiy.webtask.command.impl.admin.update.*;
import by.petrorvskiy.webtask.command.impl.common.*;
import by.petrorvskiy.webtask.command.impl.common.find.FindAllSearchApplicationByUserIdCommand;
import by.petrorvskiy.webtask.command.impl.common.find.FindApplicationInformationByIdCommand;
import by.petrorvskiy.webtask.command.impl.common.find.FindNewsCommand;
import by.petrorvskiy.webtask.command.impl.common.go.*;
import by.petrorvskiy.webtask.command.impl.signin.LogOutCommand;
import by.petrorvskiy.webtask.command.impl.signin.LogInCommand;
import by.petrorvskiy.webtask.command.impl.signin.SignUpCommand;


public enum CommandType {
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),
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
    DELETE_APPLICATION_BY_USER_ID(new DeleteSearchApplicationByUserIdCommand()),
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
    DELETE_APPLICATION(new DeleteApplicationCommand()),
    FIND_WANTED_CRIMINALS(new FindWantedCriminalByNameCommand()),
    TO_PERSONAL_INFO_CHANGE(new ToChangeUserInfoCommand()),
    CHANGE_PERSONAL_INFO(new ChangeUserInfoCommand()),
    FIND_ALL_APPLICATIONS(new FindAllApplicationsCommand()),
    FIND_SEARCH_APPLICATIONS_BY_USER_ID(new FindAllSearchApplicationByUserIdCommand()),
    FIND_APPLICATION_INFORMATION_BY_ID(new FindApplicationInformationByIdCommand()),
    FIND_USER_BY_ID(new FindUserById()),
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
