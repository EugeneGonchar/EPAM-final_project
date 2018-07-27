package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty("path.page.main");
        sessionRequestContent.removeSessionAttribute("user");
        return new ActionPageContainer(page, URLAction.REDIRECT);
    }

}
