package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.UserDTO;
import domain.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeNameSurnameCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangeNameSurnameCommand.class);

    private static final String MESSAGE_EMPTY_FIELDS = "message.emptyfield";

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
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_NAME_SURNAME_ERROR, MESSAGE_EMPTY_FIELDS);
        } catch (ServiceException e){
            logger.log(Level.INFO, "Changing name and surname failed!", e);
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
