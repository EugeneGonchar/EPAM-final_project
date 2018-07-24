package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import service.exception.WrongPasswordException;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;
import service.exception.PasswordShorter6SymbolsException;
import service.exception.PasswordsUnequalException;

public class ChangePasswordCommand implements ActionCommand {


    private final String PARAM_NAME_OLD_PASSWORD = "old_password";
    private final String PARAM_NAME_NEW_PASSWORD = "new_password";
    private final String PARAM_NAME_NEW_PASSWORD2 = "new_password2";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        System.out.println(userDTO.getLogin());

        try{
            userService.changePassword(userDTO);

            user.setPassword(userDTO.getPassword());

            sessionRequestContent.add2SessionAttributes("user", user);
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updatePasswordError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (PasswordShorter6SymbolsException e){
            sessionRequestContent.add2RequestAttributes("updatePasswordError",
                    MessageManager.getProperty("message.shortpassword"));
        } catch (PasswordsUnequalException e){
            sessionRequestContent.add2RequestAttributes("updatePasswordError",
                    MessageManager.getProperty("message.unequalpasswords"));
        } catch (WrongPasswordException e){
            sessionRequestContent.add2RequestAttributes("updatePasswordError",
                    MessageManager.getProperty("message.passworderror"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute("user")).getLogin());
        userDTO.setPassword(sessionRequestContent.getRequestParameter(PARAM_NAME_OLD_PASSWORD));
        userDTO.setPassword2(sessionRequestContent.getRequestParameter(PARAM_NAME_NEW_PASSWORD));
        userDTO.setPassword3(sessionRequestContent.getRequestParameter(PARAM_NAME_NEW_PASSWORD2));

        return userDTO;
    }

}
