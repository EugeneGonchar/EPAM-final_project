package command.command.user;

import command.ActionCommand;
import controller.content.SessionRequestContent;
import dao.exception.user.EmailExistException;
import dao.exception.user.LoginExistException;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeEmailCommand implements ActionCommand {

    private final String PARAM_NAME_EMAIL = "email";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeEmail(userDTO);

            user.setEmail(userDTO.getEmail());

            sessionRequestContent.add2SessionAttributes("user", user);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updateEmailError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (EmailExistException e){
            sessionRequestContent.add2RequestAttributes("updateEmailError",
                    MessageManager.getProperty("message.emailexist"));
        }

        page = ConfigurationManager.getProperty("path.page.contacts");

        return page;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute("user")).getLogin());
        userDTO.setEmail(sessionRequestContent.getRequestParameter(PARAM_NAME_EMAIL));

        return userDTO;
    }

}
