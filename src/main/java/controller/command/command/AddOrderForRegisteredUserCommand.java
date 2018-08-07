package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Order;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

public class AddOrderForRegisteredUserCommand  implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        Order order = (Order)sessionRequestContent.getSessionAttribute("order");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        orderService.insertOrder(order);

        sessionRequestContent.removeSessionAttribute("order");
        sessionRequestContent.removeSessionAttribute("pickupAddressOfOrder");
        sessionRequestContent.removeSessionAttribute("dropoffAddressOfOrder");
        sessionRequestContent.removeSessionAttribute("rentDays");

        page = ConfigurationManager.getProperty("path.page.successfullorder");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
