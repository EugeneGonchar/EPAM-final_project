package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.DateHelper;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.OrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Car;
import pojo.entity.Order;
import resource.ConfigurationManager;
import service.CarService;
import service.ServiceFactory;

import java.util.List;

public class GetCarsCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Car> carList = null;
        Order order = (Order) sessionRequestContent.getSessionAttribute("order");
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));

        int rentDays = DateHelper.getCeilDaysOfDateDifference(order.getReturnDate(), order.getDateReceived());

        CarService carService = ServiceFactory.getInstance().getCarService();

        carList = carService.getFreeCarList(createOrderDTO(order), pageDTO);

        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2SessionAttributes("rentDays", rentDays);
        sessionRequestContent.add2RequestAttributes("carList", carList);
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
