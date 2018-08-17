package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.PageDTO;
import pojo.entity.Car;
import resource.ConfigurationManager;
import service.CarService;
import service.factory.ServiceFactory;

import java.util.List;

public class GetCarsTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Car> carList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        CarService carService = ServiceFactory.getInstance().getCarService();

        carList = carService.getCarList(pageDTO);

        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        sessionRequestContent.add2RequestAttributes(Constant.CAR_LIST, carList);
        page = ConfigurationManager.getProperty("path.page.admin.cars");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
