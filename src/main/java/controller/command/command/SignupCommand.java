package controller.command.command;

import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dao.exception.user.EmailExistException;
import dao.exception.user.LoginExistException;
import dto.UserDTO;
import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

public class SignupCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD2 = "password2";

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
            sessionRequestContent.add2RequestAttributes("registrationError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (PasswordShorter6SymbolsException e){
            sessionRequestContent.add2RequestAttributes("registrationError",
                    MessageManager.getProperty("message.shortpassword"));
        } catch(PasswordsUnequalException e){
            sessionRequestContent.add2RequestAttributes("registrationError",
                    MessageManager.getProperty("message.unequalpasswords"));
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes("registrationError",
                    MessageManager.getProperty("message.loginexist"));
        } catch (EmailExistException e){
            sessionRequestContent.add2RequestAttributes("registrationError",
                    MessageManager.getProperty("message.emailexist"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.signup");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }


    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(sessionRequestContent.getRequestParameter(PARAM_NAME_LOGIN));
        userDTO.setFirstName(sessionRequestContent.getRequestParameter(PARAM_NAME_FIRST_NAME));
        userDTO.setLastName(sessionRequestContent.getRequestParameter(PARAM_NAME_LAST_NAME));
        userDTO.setEmail(sessionRequestContent.getRequestParameter(PARAM_NAME_EMAIL));
        userDTO.setPhone(sessionRequestContent.getRequestParameter(PARAM_NAME_PHONE));
        userDTO.setPassword(sessionRequestContent.getRequestParameter(PARAM_NAME_PASSWORD));
        userDTO.setPassword2(sessionRequestContent.getRequestParameter(PARAM_NAME_PASSWORD2));

        return userDTO;
    }
}
