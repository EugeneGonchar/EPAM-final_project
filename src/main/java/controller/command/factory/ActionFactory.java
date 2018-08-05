package controller.command.factory;

import controller.command.ActionCommand;
import controller.command.client.CommandEnum;
import controller.content.SessionRequestContent;
import resource.MessageManager;

public class ActionFactory {

    private static final String INPUT_NAME = "command";

    public ActionCommand defineCommand(SessionRequestContent sessionRequestContent){
        ActionCommand current = null;
        String action = sessionRequestContent.getRequestParameter(INPUT_NAME);

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e){
            sessionRequestContent.add2RequestAttributes("wrongAction", action +
                    MessageManager.getProperty("message.wrongaction"));
            sessionRequestContent.insertRequestAttributes();
        }
        return current;
    }

}
