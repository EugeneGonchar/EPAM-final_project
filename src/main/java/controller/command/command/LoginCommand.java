package controller.command.command;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.UserDTO;
import domain.entity.Role;
import domain.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.RoleService;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class LoginCommand implements ActionCommand {

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
            if(user == null){
                sessionRequestContent.add2RequestAttributes(Constant.LOGIN_ERROR,
                        MessageManager.getProperty("message.loginpassworderror"));
            } else {
                role = roleService.getRoleOfUser(user);
                sessionRequestContent.add2SessionAttributes(Constant.USER, user);
                sessionRequestContent.add2SessionAttributes(Constant.ROLE, role);
                page = ConfigurationManager.getProperty("path.page.main");
                actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
            }
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2RequestAttributes(Constant.LOGIN_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.login");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUserDTO(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(sessionRequestContent.getRequestParameter(Constant.LOGIN));
        userDTO.setPassword(sessionRequestContent.getRequestParameter(Constant.PASSWORD));

        return userDTO;
    }
}
