package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.DateHelper;
import controller.command.util.OrderProcessStatusConstant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.OrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Address;
import pojo.entity.Car;
import pojo.entity.Order;
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
        List<Car> carList = null;

        AddressService addressService = ServiceFactory.getInstance().getAddressService();

        stringPickupAddress = sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_LOCATION);
        stringDropoffAddress = sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_LOCATION);

        pickupAddress = addressService.formingAddressFromString(stringPickupAddress);
        dropoffAddress = addressService.formingAddressFromString(stringDropoffAddress);
        order = createOrder(sessionRequestContent);

        order.setPickupAddressId(pickupAddress.getId());
        order.setDropoffAddressId(dropoffAddress.getId());

        int rentDays = DateHelper.getCeilDaysOfDateDifference(order.getReturnDate(), order.getDateReceived());/*WTF????? 500 Server error here*/

        CarService carService = ServiceFactory.getInstance().getCarService();

        PageDTO pageDTO = new PageDTO();
        if (sessionRequestContent.getRequestParameter("elementsOnPage") == null || sessionRequestContent.getRequestParameter("page") == null){
            pageDTO.setElementsOnPage(10);
            pageDTO.setCurrentPage(1);
        } else {
            pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
            pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));
        }
        carList = carService.getFreeCarList(createOrderDTO(order), pageDTO);

        sessionRequestContent.add2SessionAttributes("carList", carList);
        sessionRequestContent.add2SessionAttributes("rentDays", rentDays);
        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2SessionAttributes("orderProcessStatus", OrderProcessStatusConstant.STATUS_READY_DATE_ADDRESS);
        sessionRequestContent.add2SessionAttributes("pickupAddressOfOrder", pickupAddress);
        sessionRequestContent.add2SessionAttributes("dropoffAddressOfOrder", dropoffAddress);
        sessionRequestContent.add2SessionAttributes("order", order);
        page = ConfigurationManager.getProperty("path.page.cars");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        
        return actionPageContainer;
    }

    private Order createOrder(SessionRequestContent sessionRequestContent){
        Order order = new Order();

        order.setDateReceived(sessionRequestContent.getRequestParameter(PARAM_NAME_PICKUP_DATE));
        order.setReturnDate(sessionRequestContent.getRequestParameter(PARAM_NAME_DROPOFF_DATE));

        return order;
    }

    private OrderDTO createOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(order.getDateReceived());
        orderDTO.setReturnDate(order.getReturnDate());
        return orderDTO;
    }
}
