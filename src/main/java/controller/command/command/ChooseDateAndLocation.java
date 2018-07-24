package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.OrderDTO;

import java.sql.Timestamp;

public class ChooseDateAndLocation implements ActionCommand {

    private static final String PARAM_NAME_PICKUP_DATE = "pickupDate";
    private static final String PARAM_NAME_DROPOFF_DATE = "dropoffDate";
    private static final String PARAM_NAME_PICKUP_DATE = "pickupDate";
    private static final String PARAM_NAME_DROPOFF_DATE = "dropoffDate";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        ActionPageContainer actionPageContainer = null;
        String page = null;

        OrderDTO orderDTO = createOrderDTO(sessionRequestContent);

        actionPageContainer = new ActionPageContainer("path.page.rent", URLAction.FORWARD);

        return actionPageContainer;
    }

    private OrderDTO createOrderDTO(SessionRequestContent sessionRequestContent){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_DATE));
        orderDTO.setReturnDate(sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_DATE));
        orderDTO.setPickupAddress(sessionRequestContent.getRequestParameter());

        return orderDTO;
    }
}
