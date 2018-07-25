package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.ConfigPage;
import controller.util.URLAction;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeNameSurnameCommand implements ActionCommand {

    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeNameSurname(userDTO);

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());

            sessionRequestContent.add2SessionAttributes("user", user);
            page = ConfigurationManager.getProperty(ConfigPage.CONFIG_PROFILE);
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updateNameSurnameError",
                    MessageManager.getProperty("message.emptyfield"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty(ConfigPage.CONFIG_PROFILE);
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute("user")).getLogin());
        userDTO.setFirstName(sessionRequestContent.getRequestParameter(PARAM_NAME_FIRST_NAME));
        userDTO.setLastName(sessionRequestContent.getRequestParameter(PARAM_NAME_LAST_NAME));

        return userDTO;
    }

}
