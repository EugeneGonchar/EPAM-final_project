package controller.command;

import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;

import javax.servlet.ServletException;
import java.io.IOException;

public interface ActionCommand {

    ActionPageContainer execute(SessionRequestContent sessionRequestContent) throws ServletException, IOException;

}
