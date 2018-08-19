package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.OrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;
import domain.entity.Order;
import resource.ConfigurationManager;
import service.CarService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import java.util.List;

public class GetCarsCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Car> carList = null;
        Order order = (Order) sessionRequestContent.getSessionAttribute(Constant.ORDER);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        CarService carService = ServiceFactory.getInstance().getCarService();

        try {
            carList = carService.getFreeCarList(createOrderDTO(order), pageDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        sessionRequestContent.add2RequestAttributes(Constant.CAR_LIST, carList);
        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        page = ConfigurationManager.getProperty("path.page.cars");

        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }

    private OrderDTO createOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setDateReceived(order.getDateReceived());
        orderDTO.setReturnDate(order.getReturnDate());
        return orderDTO;
    }
}
