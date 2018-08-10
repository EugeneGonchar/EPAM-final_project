package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.UserDTO;
import pojo.entity.Role;
import pojo.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.RoleService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        /*String to = "mr.evgenii2012@gmail.com";
        String from = "sender@gmail.com";
        String host = "127.0.0.1";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject line!");
            message.setText("This is actual message");
            Transport.send(message);
            System.out.println("Sent message success ================");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUserDTO(sessionRequestContent);

        UserService userService = ServiceFactory.getInstance().getUserService();
        RoleService roleService = ServiceFactory.getInstance().getRoleService();

        User user = null;
        Role role = null;

        try{
            user = userService.logIn(userDTO);
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2RequestAttributes("loginError",
                    MessageManager.getProperty("message.emptyfield"));
        }

        if(user == null){
            sessionRequestContent.add2RequestAttributes("loginError",
                    MessageManager.getProperty("message.loginpassworderror"));
        } else{
            role = roleService.getRoleOfUser(user);
            sessionRequestContent.add2SessionAttributes("user", user);
            sessionRequestContent.add2SessionAttributes("role", role);
            page = ConfigurationManager.getProperty("path.page.main");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.login");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUserDTO(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(sessionRequestContent.getRequestParameter(PARAM_NAME_LOGIN));
        userDTO.setPassword(sessionRequestContent.getRequestParameter(PARAM_NAME_PASSWORD));

        return userDTO;
    }
}
