package controller.command.command.admin;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.PageDTO;
import domain.dto.UserRoleDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.UserService;

import java.util.List;

public class GetUsersTableCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(GetUsersTableCommand.class);

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<UserRoleDTO> userRoleDTOList = null;
        PageDTO pageDTO = new PageDTO();
        pageDTO.setElementsOnPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.ELEMENTS_ON_PAGE)));
        pageDTO.setCurrentPage(Integer.parseInt(sessionRequestContent.getRequestParameter(Constant.PAGE)));

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            userRoleDTOList = userService.getUserRoleList(pageDTO);
        } catch (ServiceException e) {
            logger.log(Level.INFO, "Getting users table failed!", e);
        }

        sessionRequestContent.add2RequestAttributes(Constant.USER_ROLE_DTO_LIST, userRoleDTOList);
        sessionRequestContent.add2SessionAttributes(Constant.PAGE_DTO, pageDTO);

        page = ConfigurationManager.getProperty("path.page.admin.users");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
