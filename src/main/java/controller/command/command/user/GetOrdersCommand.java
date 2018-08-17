package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.FullOrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.User;
import resource.ConfigurationManager;
import service.OrderService;
import service.factory.ServiceFactory;

import java.util.List;

public class GetOrdersCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<FullOrderDTO> orderList = null;
        PageDTO pageDTO = new PageDTO();
        User user = (User)sessionRequestContent.getSessionAttribute(Constant.USER);
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        orderList = orderService.getFullUserOrders(user, pageDTO);

        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        sessionRequestContent.add2SessionAttributes(Constant.ORDER_LIST, orderList);
        page = ConfigurationManager.getProperty("path.page.orders");

        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
