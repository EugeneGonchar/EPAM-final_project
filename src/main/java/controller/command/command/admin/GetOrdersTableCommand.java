package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import java.util.List;

public class GetOrdersTableCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(GetOrdersTableCommand.class);

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<FullUserOrderDTO> fullUserOrderDTOList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try {
            fullUserOrderDTOList = orderService.getOrdersList(pageDTO);
        } catch (ServiceException e) {
            logger.log(Level.INFO, "Getting orders table failed!", e);
        }

        sessionRequestContent.add2RequestAttributes(Constant.FULL_USER_ORDER_DTO_LIST, fullUserOrderDTOList);
        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);

        page = ConfigurationManager.getProperty("path.page.admin.orders");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        return actionPageContainer;
    }
}
