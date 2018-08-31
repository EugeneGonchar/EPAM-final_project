package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class UpdateStatusCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(UpdateStatusCommand.class);

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;

        String newStatus = sessionRequestContent.getRequestParameter(Constant.SELECT_STATUS);

        try {
            updateStatus(sessionRequestContent, newStatus);
        } catch (ServiceException e) {
            logger.log(Level.INFO, "Updating accident status failed!", e);
        }

        page = ConfigurationManager.getProperty("path.page.admin.get_orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        return actionPageContainer;
    }

    private void updateStatus(SessionRequestContent sessionRequestContent, String newStatus) throws ServiceException {
        int orderId = Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ORDER_ID));
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        switch (newStatus){
            case "accident":
                break;
            case "confirmed":
                orderService.updateOrderStatus(orderId, newStatus);
                break;
            case "dismissed":
                String description = sessionRequestContent.getRequestParameter(Constant.DISMISSED_DESCRIPTION);
                orderService.updateOrderStatus(orderId, newStatus, description);
                break;
        }
    }
}
