package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

public class UpdateStatusCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;

        String newStatus = sessionRequestContent.getRequestParameter("select_status");

        updateStatus(sessionRequestContent, newStatus);

        page = ConfigurationManager.getProperty("path.page.admin.get_orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        return actionPageContainer;
    }

    private void updateStatus(SessionRequestContent sessionRequestContent, String newStatus){
        int orderId = Integer.parseInt(sessionRequestContent.getRequestParameter("order_id"));
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        switch (newStatus){
            case "accident":
                break;
            case "confirmed":
                orderService.updateOrderStatus(orderId, newStatus);
                break;
            case "dismissed":
                String description = sessionRequestContent.getRequestParameter("dismissed_description");
                orderService.updateOrderStatus(orderId, newStatus, description);
                break;
        }
    }
}
