package controller.command;

import controller.content.SessionRequestContent;
import resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand{

    @Override
    public String execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }

}
