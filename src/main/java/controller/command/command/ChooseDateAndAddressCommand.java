package controller.command.command;

import controller.command.ActionCommand;
import controller.command.util.DateHelper;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.OrderDTO;
import entity.Address;
import entity.Car;
import entity.Order;
import resource.ConfigurationManager;
import service.AddressService;
import service.CarService;
import service.ServiceFactory;

import java.util.List;

public class ChooseDateAndAddressCommand implements ActionCommand {

    private static final String PARAM_NAME_PICKUP_LOCATION = "pickupAddress";
    private static final String PARAM_NAME_DROPOFF_LOCATION = "dropoffAddress";
    private static final String PARAM_NAME_PICKUP_DATE = "pickupDate";
    private static final String PARAM_NAME_DROPOFF_DATE = "dropoffDate";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        ActionPageContainer actionPageContainer = null;
        String page = null;
        String stringPickupAddress = null;
        String stringDropoffAddress = null;
        Order order = null;
        Address pickupAddress = null;
        Address dropoffAddress = null;

        AddressService addressService = ServiceFactory.getInstance().getAddressService();

        stringPickupAddress = sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_LOCATION);
        stringDropoffAddress = sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_LOCATION);

        pickupAddress = addressService.formingAddressFromString(stringPickupAddress);
        dropoffAddress = addressService.formingAddressFromString(stringDropoffAddress);
        order = createOrder(sessionRequestContent);

        order.setPickupAddressId(pickupAddress.getId());
        order.setDropoffAddressId(dropoffAddress.getId());

        sessionRequestContent.add2SessionAttributes("pickupAddressOfOrder", pickupAddress);
        sessionRequestContent.add2SessionAttributes("dropoffAddressOfOrder", dropoffAddress);
        sessionRequestContent.add2SessionAttributes("order", order);
        page = ConfigurationManager.getProperty("path.page.preparedcars");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        
        return actionPageContainer;
    }

    private Order createOrder(SessionRequestContent sessionRequestContent){
        Order order = new Order();

        order.setDateReceived(sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_DATE));
        order.setReturnDate(sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_DATE));

        return order;
    }
}
