package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dao.exception.user.IncorrectLoginOrPasswordException;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUserDTO(sessionRequestContent);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            User user = userService.logIn(userDTO);
            /*сделать проверку на юзера*/
            sessionRequestContent.add2SessionAttributes("user", user);
            page = ConfigurationManager.getProperty("path.page.main");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2RequestAttributes("loginError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (IncorrectLoginOrPasswordException e){
            sessionRequestContent.add2RequestAttributes("loginError",
                    MessageManager.getProperty("message.loginpassworderror"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.login");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUserDTO(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(sessionRequestContent.getRequestParameter(PARAM_NAME_LOGIN));
        userDTO.setPassword(sessionRequestContent.getRequestParameter(PARAM_NAME_PASSWORD));

        return userDTO;
    }
}
