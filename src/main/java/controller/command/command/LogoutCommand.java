package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.ConfigPage;
import controller.util.URLAction;
import resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty(ConfigPage.CONFIG_MAIN);
        sessionRequestContent.removeSessionAttribute("user");
        return new ActionPageContainer(page, URLAction.REDIRECT);
    }

}
