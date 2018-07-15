package command;

import controller.content.SessionRequestContent;
import dao.exception.DAOException;
import service.exception.ServiceException;

public interface ActionCommand {

    String execute(SessionRequestContent sessionRequestContent);

}
