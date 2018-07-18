package command;

import controller.content.SessionRequestContent;

public interface ActionCommand {

    String execute(SessionRequestContent sessionRequestContent);

}
