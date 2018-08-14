package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.PageDTO;
import pojo.entity.Accident;
import resource.ConfigurationManager;
import service.AccidentService;
import service.ServiceFactory;

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
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));

        AccidentService accidentService = ServiceFactory.getInstance().getAccidentService();

        accidentList = accidentService.getAccidentList(pageDTO);

        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2RequestAttributes("accidentList", accidentList);
        page = ConfigurationManager.getProperty("path.page.admin.get_accidents");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
