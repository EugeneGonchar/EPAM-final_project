package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import dao.exception.user.LoginExistException;
import dto.UserDTO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeLoginCommand implements ActionCommand {

    private final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeLogin(userDTO);

            user.setLogin(userDTO.getLogin());

            sessionRequestContent.add2SessionAttributes("user", user);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes("updateLoginError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes("updateLoginError",
                    MessageManager.getProperty("message.loginexist"));
        }

        page = ConfigurationManager.getProperty("path.page.account");

        return page;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(((User) sessionRequestContent.getSessionAttribute("user")).getEmail());
        userDTO.setLogin(sessionRequestContent.getRequestParameter(PARAM_NAME_LOGIN));

        return userDTO;
    }

}
