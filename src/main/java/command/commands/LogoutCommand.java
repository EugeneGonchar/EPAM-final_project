package command.commands;

import command.ActionCommand;
import controller.content.SessionRequestContent;
import resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent){
        String page = ConfigurationManager.getProperty("path.page.index");
        sessionRequestContent.removeSessionAttribute("user");
        return page;
    }

}
