package controller.command.command;

import controller.command.command.user.ChangePasswordCommand;
import controller.command.util.Constant;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.*;
import domain.dto.UserDTO;
import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import resource.ConfigurationManager;
import service.factory.ServiceFactory;
import service.UserService;

public class SignupCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(SignupCommand.class);

    private static final String MESSAGE_EMPTY_FIELDS = "message.emptyfield";
    private static final String MESSAGE_SHORT_PASSWORD = "message.shortpassword";
    private static final String MESSAGE_UNEQUAL_PASSWORDS = "message.unequalpasswords";
    private static final String MESSAGE_LOGIN_EXIST = "message.loginexist";
    private static final String MESSAGE_EMAIL_EXIST = "message.emailexist";
    private static final String MESSAGE_PASSPORT_DATA_INVALID = "message.passporddatainvalid";
    private static final String MESSAGE_EMAIL_INVALID = "message.emailinvalid";
    private static final String MESSAGE_PHONE_INVALID = "message.phoneinvalid";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.signUp(userDTO);
            page = ConfigurationManager.getProperty("path.page.successfullsignup");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_EMPTY_FIELDS);
        } catch (PasswordShorter6SymbolsException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_SHORT_PASSWORD);
        } catch(PasswordsUnequalException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_UNEQUAL_PASSWORDS);
        } catch (LoginExistException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_LOGIN_EXIST);
        } catch (EmailExistException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_EMAIL_EXIST);
        } catch (PassportDataInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_PASSPORT_DATA_INVALID);
        } catch (EmailInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_EMAIL_INVALID);
        } catch (PhoneInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.REGISTRATION_ERROR, MESSAGE_PHONE_INVALID);
        } catch (ServiceException e){
            logger.log(Level.INFO, "Signing up failed!", e);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.signup");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        }

        return actionPageContainer;
    }


    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(sessionRequestContent.getRequestParameter(Constant.LOGIN));
        userDTO.setFirstName(sessionRequestContent.getRequestParameter(Constant.FIRST_NAME));
        userDTO.setLastName(sessionRequestContent.getRequestParameter(Constant.LAST_NAME));
        userDTO.setEmail(sessionRequestContent.getRequestParameter(Constant.EMAIL));
        userDTO.setPhone(sessionRequestContent.getRequestParameter(Constant.PHONE));
        userDTO.setPassword(sessionRequestContent.getRequestParameter(Constant.PASSWORD));
        userDTO.setPassword2(sessionRequestContent.getRequestParameter(Constant.PASSWORD2));

        return userDTO;
    }
}
