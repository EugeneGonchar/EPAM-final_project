package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangePhoneCommand implements ActionCommand {

    private static final String PARAM_NAME_PHONE = "phone";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changePhone(userDTO);

            user.setPhone(userDTO.getPhone());

            sessionRequestContent.add2SessionAttributes("user", user);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updatePhoneError",
                    MessageManager.getProperty("message.emptyfield"));
        }

        page = ConfigurationManager.getProperty("path.page.contacts");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute("user")).getLogin());
        userDTO.setPhone(sessionRequestContent.getRequestParameter(PARAM_NAME_PHONE));
        return userDTO;
    }
}
