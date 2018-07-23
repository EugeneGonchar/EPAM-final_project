package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty("path.page.main");
        sessionRequestContent.removeSessionAttribute("user");
        return page;
    }

}
