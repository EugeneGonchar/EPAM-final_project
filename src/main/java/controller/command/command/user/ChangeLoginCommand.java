package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.ConfigPage;
import controller.util.URLAction;
import service.exception.LoginExistException;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeLoginCommand implements ActionCommand {

    private final String PARAM_NAME_LOGIN = "login";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeLogin(userDTO);

            user.setLogin(userDTO.getLogin());

            sessionRequestContent.add2SessionAttributes("user", user);
            page = ConfigurationManager.getProperty(ConfigPage.CONFIG_ACCOUNT);
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updateLoginError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes("updateLoginError",
                    MessageManager.getProperty("message.loginexist"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty(ConfigPage.CONFIG_ACCOUNT);
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(((User) sessionRequestContent.getSessionAttribute("user")).getEmail());
        userDTO.setLogin(sessionRequestContent.getRequestParameter(PARAM_NAME_LOGIN));

        return userDTO;
    }

}
