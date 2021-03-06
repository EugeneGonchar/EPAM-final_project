package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.Order;
import domain.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class AddOrderCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddOrderCommand.class);

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        User registeredUser = null;

        Order order = (Order)sessionRequestContent.getSessionAttribute(Constant.ORDER);
        User user = (User)sessionRequestContent.getSessionAttribute(Constant.USER);
        User guestUser = (User)sessionRequestContent.getSessionAttribute(Constant.GUEST_USER);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try{
            if(user != null){
                order.setUserId(user.getId());
                orderService.insertOrder(order);
            } else{
                registeredUser = orderService.insertOrder(order, guestUser);
            }
        } catch (ServiceException e){
            logger.log(Level.INFO, "Adding order failed!", e);
        }

        sessionRequestContent.removeSessionAttribute(Constant.ORDER);
        sessionRequestContent.removeSessionAttribute(Constant.PICKUP_ADDRESS_OF_ORDER);
        sessionRequestContent.removeSessionAttribute(Constant.DROPOFF_ADDRESS_OF_ORDER);
        sessionRequestContent.removeSessionAttribute(Constant.RENT_DAYS);

        sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_CONFIRMATION);
        sessionRequestContent.add2SessionAttributes(Constant.REGISTERED_USER, registeredUser);

        page = ConfigurationManager.getProperty("path.page.successfullorder");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
