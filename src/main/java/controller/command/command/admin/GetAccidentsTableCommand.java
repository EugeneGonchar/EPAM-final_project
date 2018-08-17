package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.PageDTO;
import domain.entity.Accident;
import resource.ConfigurationManager;
import service.AccidentService;
import service.factory.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetAccidentsTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) throws ServletException, IOException {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Accident> accidentList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        AccidentService accidentService = ServiceFactory.getInstance().getAccidentService();

        accidentList = accidentService.getAccidentList(pageDTO);

        sessionRequestContent.add2RequestAttributes(Constant.ACCIDENT_LIST, accidentList);
        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);

        page = ConfigurationManager.getProperty("path.page.admin.accidents");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
