package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.PageDTO;
import pojo.dto.UserRoleDTO;
import resource.ConfigurationManager;
import service.ServiceFactory;
import service.UserService;

import java.util.List;

public class GetUsersTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<UserRoleDTO> userRoleDTOList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter("elementsOnPage")));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter("page")));

        UserService userService = ServiceFactory.getInstance().getUserService();

        userRoleDTOList = userService.getUserRoleList(pageDTO);

        sessionRequestContent.add2SessionAttributes("pageDTO", pageDTO);
        sessionRequestContent.add2RequestAttributes("userRoleDTOList", userRoleDTOList);
        page = ConfigurationManager.getProperty("path.page.admin.users");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
