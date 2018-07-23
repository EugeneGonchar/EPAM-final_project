package controller.command;

import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;

public interface ActionCommand {

    ActionPageContainer execute(SessionRequestContent sessionRequestContent);

}
