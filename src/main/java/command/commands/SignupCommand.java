package command.commands;

import command.ActionCommand;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SignupCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String firstName = request.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = request.getParameter(PARAM_NAME_LAST_NAME);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        Map<String, String> params = new HashMap<String, String>();
        params.put(PARAM_NAME_LOGIN, login);
        params.put(PARAM_NAME_EMAIL, email);
        params.put(PARAM_NAME_PHONE, phone);
        params.put(PARAM_NAME_FIRST_NAME, firstName);
        params.put(PARAM_NAME_LAST_NAME, lastName);
        params.put(PARAM_NAME_PASSWORD, pass);

        if (userServiceImpl.signUp(params)){
            page = ConfigurationManager.getProperty("path.page.successfullsignup");
        } else{
            request.setAttribute("loginExist",
                    MessageManager.getProperty("message.loginexist"));
            page = ConfigurationManager.getProperty("path.page.signup");
        }

        return page;
    }

}
