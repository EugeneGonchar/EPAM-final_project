package controller.command.command.user;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.FullOrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.User;
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
        PageDTO pageDTO = new PageDTO();
        User user = (User)sessionRequestContent.getSessionAttribute("user");
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        orderList = orderService.getFullUserOrders(user, pageDTO);

        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2SessionAttributes("orderList", orderList);
        page = ConfigurationManager.getProperty("path.page.orders");

        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
