package command.commands;

import command.ActionCommand;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        if (userServiceImpl.logIn(login, pass) != null){
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.logout");
        } else{
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
/*
        if(LoginLogic.checkLogin(login, pass)){
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.main");
        } else{
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }*/
        return page;
    }
}
