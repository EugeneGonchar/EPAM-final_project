package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.CarDTO;
import domain.entity.Address;
import domain.entity.Car;
import domain.entity.Order;
import resource.ConfigurationManager;
import service.CarService;
import service.factory.ServiceFactory;
import service.impl.OrderServiceImpl;

public class GetDriverDetailsPageCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        CarDTO carDTO = null;
        Car car = null;

        Address pickupAddress = (Address)sessionRequestContent.getSessionAttribute(Constant.PICKUP_ADDRESS);
        Address dropoffAddress = (Address)sessionRequestContent.getSessionAttribute(Constant.DROPOFF_ADDRESS);
        Order order = (Order)sessionRequestContent.getSessionAttribute(Constant.ORDER);
        Integer rentDays = (Integer) sessionRequestContent.getSessionAttribute(Constant.RENT_DAYS);

        carDTO = createCarDTO(sessionRequestContent);

        CarService carService = ServiceFactory.getInstance().getCarService();

        car = carService.getCar(carDTO);

        order.setCarId(car.getId());
        order.setTotalCost(OrderServiceImpl.getCalculatedTotalCost(car, rentDays));

        sessionRequestContent.removeSessionAttribute(Constant.CAR_LIST);

        sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_CAR);
        sessionRequestContent.add2SessionAttributes(Constant.CAR, car);

        page = ConfigurationManager.getProperty("path.page.driverdetails");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }

    private CarDTO createCarDTO(SessionRequestContent sessionRequestContent){
        CarDTO carDTO = new CarDTO();

        int id = Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.CAR_ID));
        carDTO.setId(id);
        return carDTO;
    }
}
