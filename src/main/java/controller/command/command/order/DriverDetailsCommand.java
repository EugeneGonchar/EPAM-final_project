package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.*;

public class DriverDetailsCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        User user = createUser(sessionRequestContent);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.checkDriverDetails(user);

            sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_DRIVER_DETAILS);
            sessionRequestContent.add2SessionAttributes(Constant.GUEST_USER, user);
            page = ConfigurationManager.getProperty("path.page.payment");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        } catch (ExistEmptyFieldException e){
            sessionRequestContent.add2RequestAttributes(Constant.DRIVER_DETAILS_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        } catch (EmailExistException e){
            sessionRequestContent.add2RequestAttributes(Constant.DRIVER_DETAILS_ERROR,
                    MessageManager.getProperty("message.emailexist"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.driverdetails");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private User createUser(SessionRequestContent sessionRequestContent){
        User user = new User();

        user.setFirstName(sessionRequestContent.getRequestParameter(Constant.FIRST_NAME));
        user.setLastName(sessionRequestContent.getRequestParameter(Constant.LAST_NAME));
        user.setEmail(sessionRequestContent.getRequestParameter(Constant.EMAIL));
        user.setPhone(sessionRequestContent.getRequestParameter(Constant.PHONE));

        return user;
    }
}
