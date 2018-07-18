package command.factory;

import command.ActionCommand;
import command.EmptyCommand;
import command.client.CommandEnum;
import controller.content.SessionRequestContent;
import resource.MessageManager;

public class ActionFactory {

    private final String INPUT_NAME = "command";

    public ActionCommand defineCommand(SessionRequestContent sessionRequestContent){
        ActionCommand current = new EmptyCommand();
        String action = sessionRequestContent.getRequestParameter(INPUT_NAME);

        if (action == null || action.isEmpty()){
            return current;
        }
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
