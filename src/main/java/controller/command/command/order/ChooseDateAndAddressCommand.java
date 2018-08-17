package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.DateHelper;
import controller.command.util.Constant;
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
import service.factory.ServiceFactory;

import java.util.List;

public class ChooseDateAndAddressCommand implements ActionCommand {

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

        stringPickupAddress = sessionRequestContent.getRequestParameter(Constant.PICKUP_ADDRESS);
        stringDropoffAddress = sessionRequestContent.getRequestParameter(Constant.DROPOFF_ADDRESS);

        pickupAddress = addressService.formingAddressFromString(stringPickupAddress);
        dropoffAddress = addressService.formingAddressFromString(stringDropoffAddress);
        order = createOrder(sessionRequestContent);

        order.setPickupAddressId(pickupAddress.getId());
        order.setDropoffAddressId(dropoffAddress.getId());

        Integer rentDays = DateHelper.getCeilDaysOfDateDifference(order.getReturnDate(), order.getDateReceived());

        CarService carService = ServiceFactory.getInstance().getCarService();

        PageDTO pageDTO = new PageDTO();
        if (sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE) == null || sessionRequestContent.getRequestParameter(Constant.PAGE) == null){
            pageDTO.setElementsOnPage(10);
            pageDTO.setCurrentPage(1);
        } else {
            pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
            pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));
        }
        carList = carService.getFreeCarList(createOrderDTO(order), pageDTO);

        sessionRequestContent.add2SessionAttributes(Constant.CAR_LIST, carList);
        sessionRequestContent.add2SessionAttributes(Constant.RENT_DAYS, rentDays);
        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_DATE_ADDRESS);
        sessionRequestContent.add2SessionAttributes(Constant.PICKUP_ADDRESS_OF_ORDER, pickupAddress);
        sessionRequestContent.add2SessionAttributes(Constant.DROPOFF_ADDRESS_OF_ORDER, dropoffAddress);
        sessionRequestContent.add2SessionAttributes(Constant.ORDER, order);
        page = ConfigurationManager.getProperty("path.page.cars");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        
        return actionPageContainer;
    }

    private Order createOrder(SessionRequestContent sessionRequestContent){
        Order order = new Order();

        order.setDateReceived(sessionRequestContent.getRequestParameter(Constant.PICKUP_DATE));
        order.setReturnDate(sessionRequestContent.getRequestParameter(Constant.DROPOFF_DATE));

        return order;
    }

    private OrderDTO createOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(order.getDateReceived());
        orderDTO.setReturnDate(order.getReturnDate());
        return orderDTO;
    }
}
