package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.OrderProcessStatusConstant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Order;
import entity.User;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

public class AddOrderCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        User registeredUser = null;

        Order order = (Order)sessionRequestContent.getSessionAttribute("order");
        User user = (User)sessionRequestContent.getSessionAttribute("user");
        User guestUser = (User)sessionRequestContent.getSessionAttribute("guestUser");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        if(user != null){
            order.setUserId(user.getId());
            orderService.insertOrder(order);
        } else{
            registeredUser = orderService.insertOrder(order, guestUser);
        }

        sessionRequestContent.removeSessionAttribute("order");
        sessionRequestContent.removeSessionAttribute("pickupAddressOfOrder");
        sessionRequestContent.removeSessionAttribute("dropoffAddressOfOrder");
        sessionRequestContent.removeSessionAttribute("rentDays");

        sessionRequestContent.add2SessionAttributes("orderProcessStatus", OrderProcessStatusConstant.STATUS_READY_CONFIRMATION);
        sessionRequestContent.add2SessionAttributes("registeredUser", registeredUser);

        page = ConfigurationManager.getProperty("path.page.successfullorder");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
