package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Car;
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

        CarService carService = ServiceFactory.getCarService();

        carList = carService.getCarList();

        sessionRequestContent.add2RequestAttributes("carList", carList);
        page = ConfigurationManager.getProperty("path.page.cars");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
