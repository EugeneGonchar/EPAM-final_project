package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.LoginExistException;
import domain.dto.UserDTO;
import domain.entity.User;
import resource.ConfigurationManager;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeLoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangeLoginCommand.class);

    private static final String MESSAGE_EMPTY_FIELDS = "message.emptyfield";
    private static final String MESSAGE_LOGIN_EXIST = "message.loginexist";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeLogin(userDTO);

            user.setLogin(userDTO.getLogin());

            sessionRequestContent.add2SessionAttributes(Constant.USER, user);
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_LOGIN_ERROR, MESSAGE_EMPTY_FIELDS);
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_LOGIN_ERROR, MESSAGE_LOGIN_EXIST);
        } catch (ServiceException e){
            logger.log(Level.INFO, "Changing login failed!", e);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(((User) sessionRequestContent.getSessionAttribute(Constant.USER)).getEmail());
        userDTO.setLogin(sessionRequestContent.getRequestParameter(Constant.LOGIN));

        return userDTO;
    }

}
