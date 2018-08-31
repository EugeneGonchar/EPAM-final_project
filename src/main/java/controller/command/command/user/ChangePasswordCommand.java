package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.*;
import domain.dto.UserDTO;
import domain.entity.User;
import resource.ConfigurationManager;
import service.factory.ServiceFactory;
import service.UserService;

public class ChangePasswordCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private static final String MESSAGE_EMPTY_FIELDS = "message.emptyfield";
    private static final String MESSAGE_SHORT_PASSWORD = "message.shortpassword";
    private static final String MESSAGE_UNEQUAL_PASSWORD = "message.unequalpasswords";
    private static final String MESSAGE_PASSWORD_ERROR = "message.passworderror";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);

        UserService userService = ServiceFactory.getInstance().getUserService();

        System.out.println(userDTO.getLogin());

        try{
            userService.changePassword(userDTO);

            user.setPassword(userDTO.getPassword());

            sessionRequestContent.add2SessionAttributes(Constant.USER, user);
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_PASSWORD_ERROR, MESSAGE_EMPTY_FIELDS);
        } catch (PasswordShorter6SymbolsException e){
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_PASSWORD_ERROR, MESSAGE_SHORT_PASSWORD);
        } catch (PasswordsUnequalException e){
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_PASSWORD_ERROR, MESSAGE_UNEQUAL_PASSWORD);
        } catch (WrongPasswordException e){
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_PASSWORD_ERROR, MESSAGE_PASSWORD_ERROR);
        } catch (ServiceException e){
            logger.log(Level.INFO, "Changing password failed!", e);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute(Constant.USER)).getLogin());
        userDTO.setPassword(sessionRequestContent.getRequestParameter(Constant.OLD_PASSWORD));
        userDTO.setPassword2(sessionRequestContent.getRequestParameter(Constant.NEW_PASSWORD));
        userDTO.setPassword3(sessionRequestContent.getRequestParameter(Constant.NEW_PASSWORD2));

        return userDTO;
    }

}
