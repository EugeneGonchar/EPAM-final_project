package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.OrderProcessStatusConstant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.ServiceFactory;
import service.UserService;
import service.exception.*;

public class DriverDetailsCommand implements ActionCommand {

    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        User user = createUser(sessionRequestContent);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.checkDriverDetails(user);

            sessionRequestContent.add2SessionAttributes("orderProcessStatus", OrderProcessStatusConstant.STATUS_READY_DRIVER_DETAILS);
            sessionRequestContent.add2SessionAttributes("guestUser", user);
            page = ConfigurationManager.getProperty("path.page.payment");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2RequestAttributes("driverDetailsError",
                    MessageManager.getProperty("message.emptyfield"));
        } catch (EmailExistException e){
            sessionRequestContent.add2RequestAttributes("driverDetailsError",
                    MessageManager.getProperty("message.emailexist"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.driverdetails");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private User createUser(SessionRequestContent sessionRequestContent){
        User user = new User();

        user.setFirstName(sessionRequestContent.getRequestParameter(PARAM_NAME_FIRST_NAME));
        user.setLastName(sessionRequestContent.getRequestParameter(PARAM_NAME_LAST_NAME));
        user.setEmail(sessionRequestContent.getRequestParameter(PARAM_NAME_EMAIL));
        user.setPhone(sessionRequestContent.getRequestParameter(PARAM_NAME_PHONE));

        return user;
    }
}
