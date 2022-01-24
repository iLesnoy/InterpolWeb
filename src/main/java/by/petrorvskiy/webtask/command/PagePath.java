package by.petrorvskiy.webtask.command;

public class PagePath {

    public static final String NEWS_FEED = "pages/newsfeed.jsp";
    public static final String MAIN = "/pages/main.jsp";
    public static final String ADMIN = "pages/admin.jsp";
    public static final String AGENT = "pages/agent.jsp";
    public static final String ACCOUNT = "pages/account.jsp";
    public static final String USER = "pages/user.jsp";
    public static final String ERROR_404 = "pages/error404.jsp";
    public static final String ERROR_500 = "pages/error500.jsp";
    public static final String SIGN_UP = "pages/signup.jsp";
    public static final String MISSING_PEOPLE = "pages/missing.jsp";
    public static final String USER_PERSONAL_INFO_CHANGE = "pages/changepersonalinfo.jsp";
    public static final String WANTED_CRIMINALS = "pages/wantedcriminals.jsp";
    public static final String ADD = "pages/add.jsp";


    public static final String TO_MAIN_PAGE="/controller?command=to_main";
    public static final String TO_NEWS_PAGE="/controller?command=to_news_feed";
    public static final String TO_LOG_IN_PAGE ="/controller?command=to_log_in";
    public static final String TO_ADMIN_PAGE="/controller?command=to_admin";
    public static final String TO_USER_PAGE="/controller?command=to_user";
    public static final String TO_AGENT_PAGE = "/controller?command=to_agent";
    public static final String TO_ACCOUNT_PAGE="/controller?command=to_account";
    public static final String TO_ADD="/controller?command=to_add";
    public static final String TO_SIGN_UP_PAGE="/controller?command=to_sign_up";
    public static final String TO_MISSING_PAGE = "/controller?command=to_missing_people";
    public static final String TO_PERSONAL_INFO_CHANGE = "/controller?command=to_personal_info_change";
    public static final Object TO_WANTED_CRIMINALS_PAGE = "/controller?command=to_wanted";;

    private PagePath(){
    }
}
