package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.*;

public class DriverDetailsCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(DriverDetailsCommand.class);

    private static final String MESSAGE_EMPTY_FIELDS = "message.emptyfield";
    private static final String MESSAGE_EMAIL_EXIST = "message.emailexist";
    private static final String MESSAGE_PASSPORT_DATA_INVALID = "message.passporddatainvalid";
    private static final String MESSAGE_EMAIL_INVALID = "message.emailinvalid";
    private static final String MESSAGE_PHONE_INVALID = "message.phoneinvalid";

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
            sessionRequestContent.add2SessionAttributes(Constant.DRIVER_DETAILS_ERROR, MESSAGE_EMPTY_FIELDS);
        } catch (EmailExistException e){
            sessionRequestContent.add2SessionAttributes(Constant.DRIVER_DETAILS_ERROR, MESSAGE_EMAIL_EXIST);
        } catch (PassportDataInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.DRIVER_DETAILS_ERROR, MESSAGE_PASSPORT_DATA_INVALID);
        } catch (EmailInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.DRIVER_DETAILS_ERROR, MESSAGE_EMAIL_INVALID);
        } catch (PhoneInvalidException e){
            sessionRequestContent.add2SessionAttributes(Constant.DRIVER_DETAILS_ERROR, MESSAGE_PHONE_INVALID);
        } catch (ServiceException e) {
            logger.log(Level.INFO, "Checking driver details failed!", e);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.driverdetails");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
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
