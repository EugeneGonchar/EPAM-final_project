package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.FullOrderDTO;
import entity.User;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

import java.util.List;

public class GetOrdersCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<FullOrderDTO> orderList = null;
        User user = (User)sessionRequestContent.getSessionAttribute("user");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        orderList = orderService.getFullUserOrders(user);

        sessionRequestContent.add2SessionAttributes("orderList", orderList);
        page = ConfigurationManager.getProperty("path.page.orders");

        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
