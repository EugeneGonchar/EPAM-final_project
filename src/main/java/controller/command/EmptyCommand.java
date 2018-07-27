package controller.command;

import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand{

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty("path.page.index");
        return new ActionPageContainer(page, URLAction.REDIRECT);
    }

}
