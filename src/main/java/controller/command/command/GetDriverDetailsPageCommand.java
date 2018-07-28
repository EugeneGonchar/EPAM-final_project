package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import dto.CarDTO;
import entity.Address;
import entity.Car;
import entity.Order;
import resource.ConfigurationManager;
import service.CarService;
import service.ServiceFactory;

public class GetDriverDetailsPageCommand implements ActionCommand {

    private static final String PARAM_NAME_PICKUP_LOCATION = "pickupAddress";
    private static final String PARAM_NAME_DROPOFF_LOCATION = "dropoffAddress";
    private static final String PARAM_NAME_ORDER = "order";
    private static final String PARAM_NAME_CAR_ID = "id";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        CarDTO carDTO = null;
        Car car = null;

        Address pickupAddress = (Address)sessionRequestContent.getSessionAttribute(PARAM_NAME_PICKUP_LOCATION);
        Address dropoffAddress = (Address)sessionRequestContent.getSessionAttribute(PARAM_NAME_DROPOFF_LOCATION);
        Order order = (Order)sessionRequestContent.getSessionAttribute(PARAM_NAME_ORDER);
        carDTO = createCarDTO(sessionRequestContent);

        CarService carService = ServiceFactory.getInstance().getCarService();

        car = carService.getCar(carDTO);

        sessionRequestContent.add2SessionAttributes("car", car);
        page = ConfigurationManager.getProperty("path.page.driverdetails");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }

    private CarDTO createCarDTO(SessionRequestContent sessionRequestContent){
        CarDTO carDTO = new CarDTO();

        int id = Integer.parseInt(sessionRequestContent.getRequestParameter(PARAM_NAME_CAR_ID));
        carDTO.setId(id);
        System.out.println(id);
        return carDTO;
    }
}
