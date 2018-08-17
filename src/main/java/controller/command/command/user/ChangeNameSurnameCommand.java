package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.UserDTO;
import pojo.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeNameSurnameCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeNameSurname(userDTO);

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());

            sessionRequestContent.add2SessionAttributes(Constant.USER, user);
            page = ConfigurationManager.getProperty("path.page.profile");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_NAME_SURNAME_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.profile");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute(Constant.USER)).getLogin());
        userDTO.setFirstName(sessionRequestContent.getRequestParameter(Constant.FIRST_NAME));
        userDTO.setLastName(sessionRequestContent.getRequestParameter(Constant.LAST_NAME));

        return userDTO;
    }

}
