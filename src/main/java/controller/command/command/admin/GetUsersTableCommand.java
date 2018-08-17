package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import pojo.dto.PageDTO;
import pojo.dto.UserRoleDTO;
import resource.ConfigurationManager;
import service.factory.ServiceFactory;
import service.UserService;

import java.util.List;

public class GetUsersTableCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<UserRoleDTO> userRoleDTOList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        UserService userService = ServiceFactory.getInstance().getUserService();

        userRoleDTOList = userService.getUserRoleList(pageDTO);

        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);
        sessionRequestContent.add2RequestAttributes(Constant.USER_ROLE_DTO_LIST, userRoleDTOList);
        page = ConfigurationManager.getProperty("path.page.admin.users");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
