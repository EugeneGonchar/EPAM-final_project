package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.FullUserOrderDTO;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

import java.util.List;

public class GetOrdersTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<FullUserOrderDTO> fullUserOrderDTOList = null;

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        fullUserOrderDTOList = orderService.getFullOrders();

        sessionRequestContent.add2RequestAttributes("fullUserOrderDTOList", fullUserOrderDTOList);
        page = ConfigurationManager.getProperty("path.page.admin.orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        return actionPageContainer;
    }
}
