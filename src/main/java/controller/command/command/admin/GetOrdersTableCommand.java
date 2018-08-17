package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.FullUserOrderDTO;
import pojo.dto.PageDTO;
import resource.ConfigurationManager;
import service.OrderService;
import service.factory.ServiceFactory;

import java.util.List;

public class GetOrdersTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<FullUserOrderDTO> fullUserOrderDTOList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        fullUserOrderDTOList = orderService.getOrdersList(pageDTO);

        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        sessionRequestContent.add2RequestAttributes(Constant.FULL_USER_ORDER_DTO_LIST, fullUserOrderDTOList);
        page = ConfigurationManager.getProperty("path.page.admin.get_orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        return actionPageContainer;
    }
}
