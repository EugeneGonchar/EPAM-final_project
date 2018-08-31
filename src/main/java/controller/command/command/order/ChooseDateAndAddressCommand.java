package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.DateInvalidException;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.OrderDTO;
import domain.dto.OrderDatesDTO;
import domain.dto.PageDTO;
import domain.entity.Address;
import domain.entity.Car;
import domain.entity.Order;
import resource.ConfigurationManager;
import service.AddressService;
import service.CarService;
import service.OrderService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import java.util.List;

public class ChooseDateAndAddressCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChooseDateAndAddressCommand.class);

    private static final String MESSAGE_DATE_INVALID = "message.dateinvalid";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {

        ActionPageContainer actionPageContainer = null;
        String page = null;
        String stringPickupAddress = null;
        String stringDropoffAddress = null;
        Order order = null;
        OrderDatesDTO orderDatesDTO = null;
        Address pickupAddress = null;
        Address dropoffAddress = null;
        List<Car> carList = null;

        AddressService addressService = ServiceFactory.getInstance().getAddressService();
        CarService carService = ServiceFactory.getInstance().getCarService();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        stringPickupAddress = sessionRequestContent.getRequestParameter(Constant.PICKUP_ADDRESS);
        stringDropoffAddress = sessionRequestContent.getRequestParameter(Constant.DROPOFF_ADDRESS);

        try {
            pickupAddress = addressService.formingAddressFromString(stringPickupAddress);
            dropoffAddress = addressService.formingAddressFromString(stringDropoffAddress);

            orderDatesDTO = createOrderDatesDTO(sessionRequestContent);
            orderService.formingOrder(orderDatesDTO);

            order = createOrder(orderDatesDTO);
            order.setPickupAddressId(pickupAddress.getId());
            order.setDropoffAddressId(dropoffAddress.getId());

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
            sessionRequestContent.add2SessionAttributes(Constant.RENT_DAYS, orderDatesDTO.getRentDays());
            sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
            sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_DATE_ADDRESS);
            sessionRequestContent.add2SessionAttributes(Constant.PICKUP_ADDRESS_OF_ORDER, pickupAddress);
            sessionRequestContent.add2SessionAttributes(Constant.DROPOFF_ADDRESS_OF_ORDER, dropoffAddress);
            sessionRequestContent.add2SessionAttributes(Constant.ORDER, order);
            page = ConfigurationManager.getProperty("path.page.cars");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (DateInvalidException e) {
            sessionRequestContent.add2SessionAttributes(Constant.DATE_LOC_ERROR, MESSAGE_DATE_INVALID);
        } catch (ServiceException e) {
            logger.log(Level.INFO, "Choosing date and place stage failed!", e);
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.get_rent");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        }

        return actionPageContainer;
    }

    private OrderDatesDTO createOrderDatesDTO(SessionRequestContent sessionRequestContent){
        OrderDatesDTO orderDatesDTO = new OrderDatesDTO();

        orderDatesDTO.setDateReceived(sessionRequestContent.getRequestParameter(Constant.PICKUP_DATE));
        orderDatesDTO.setReturnDate(sessionRequestContent.getRequestParameter(Constant.DROPOFF_DATE));

        return orderDatesDTO;
    }

    private Order createOrder(OrderDatesDTO orderDatesDTO){
        Order order = new Order();

        order.setDateReceived(orderDatesDTO.getDateReceived());
        order.setReturnDate(orderDatesDTO.getReturnDate());

        return order;
    }

    private OrderDTO createOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(order.getDateReceived());
        orderDTO.setReturnDate(order.getReturnDate());
        return orderDTO;
    }
}
