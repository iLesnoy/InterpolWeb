package epam.task.web.command;

public class PagePath {

    public static final String NEWS_FEED = "pages/newsfeed.jsp";
    public static final String MAIN = "pages/main.jsp";
    public static final String ADMIN = "pages/admin.jsp";
    public static final String USER = "pages/user.jsp";
    public static final String ERROR = "pages/error.jsp";
    public static final String SIGN_IN = "pages/signing.jsp";
    public static final String SIGN_UP = "pages/signup.jsp";

    public static final String TO_MAIN_PAGE="/controller?command=to_main";
    public static final String TO_NEWS_PAGE="/controller?command=to_news_feed";
    public static final String TO_ADMIN_PAGE="/controller?command=to_admin";
    public static final String TO_USER_PAGE="/controller?command=to_user";

    private PagePath(){
    }
}
