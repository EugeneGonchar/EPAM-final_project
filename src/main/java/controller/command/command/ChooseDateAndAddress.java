package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.ConfigPage;
import controller.util.URLAction;
import dto.OrderDTO;
import entity.Address;
import entity.Order;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

import java.sql.Timestamp;

public class ChooseDateAndAddress implements ActionCommand {

    private static final String PARAM_NAME_PICKUP_LOCATION = "pickupAddress";
    private static final String PARAM_NAME_DROPOFF_LOCATION = "dropoffAddress";
    private static final String PARAM_NAME_PICKUP_DATE = "pickupDate";
    private static final String PARAM_NAME_DROPOFF_DATE = "dropoffDate";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        ActionPageContainer actionPageContainer = null;
        String page = null;
        Order order = null;

        OrderDTO orderDTO = createOrderDTO(sessionRequestContent);

        OrderService orderService = ServiceFactory.getOrderService();

        order = orderService.formingDateAddressOfOrder(orderDTO);

        sessionRequestContent.add2SessionAttributes("order", order);
        page = ConfigurationManager.getProperty(ConfigPage.CONFIG_PREPARED_CARS);
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }

    private OrderDTO createOrderDTO(SessionRequestContent sessionRequestContent){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_DATE));
        orderDTO.setReturnDate(sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_DATE));
        orderDTO.setPickupAddress(sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_LOCATION));
        orderDTO.setDropoffAddress(sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_LOCATION));

        return orderDTO;
    }
}
