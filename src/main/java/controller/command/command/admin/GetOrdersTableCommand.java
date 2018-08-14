package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.FullUserOrderDTO;
import pojo.dto.PageDTO;
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
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        fullUserOrderDTOList = orderService.getOrdersList(pageDTO);

        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2RequestAttributes("fullUserOrderDTOList", fullUserOrderDTOList);
        page = ConfigurationManager.getProperty("path.page.admin.get_orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        return actionPageContainer;
    }
}
