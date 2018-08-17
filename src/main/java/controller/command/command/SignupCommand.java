package controller.command.command;

import controller.command.util.Constant;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import service.exception.EmailExistException;
import service.exception.LoginExistException;
import domain.dto.UserDTO;
import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

public class SignupCommand implements ActionCommand {

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
            sessionRequestContent.add2RequestAttributes(Constant.REGISTRATION_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        } catch (PasswordShorter6SymbolsException e){
            sessionRequestContent.add2RequestAttributes(Constant.REGISTRATION_ERROR,
                    MessageManager.getProperty("message.shortpassword"));
        } catch(PasswordsUnequalException e){
            sessionRequestContent.add2RequestAttributes(Constant.REGISTRATION_ERROR,
                    MessageManager.getProperty("message.unequalpasswords"));
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes(Constant.REGISTRATION_ERROR,
                    MessageManager.getProperty("message.loginexist"));
        } catch (EmailExistException e){
            sessionRequestContent.add2RequestAttributes(Constant.REGISTRATION_ERROR,
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
