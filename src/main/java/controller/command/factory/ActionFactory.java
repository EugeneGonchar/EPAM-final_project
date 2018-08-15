package controller.command.factory;

import controller.command.ActionCommand;
import controller.command.EmptyCommand;
import controller.command.client.CommandEnum;
import controller.content.SessionRequestContent;
import resource.MessageManager;

public class ActionFactory {

    private static final String INPUT_NAME = "command";

    public ActionCommand defineCommand(SessionRequestContent sessionRequestContent){
        ActionCommand current = new EmptyCommand();
        String action = sessionRequestContent.getRequestParameter(INPUT_NAME);

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            System.out.println("currentEnum = " + currentEnum.name());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e){
            System.out.println("no command");
            sessionRequestContent.add2RequestAttributes("wrongAction", action +
                    MessageManager.getProperty("message.wrongaction"));
            sessionRequestContent.insertRequestAttributes();
        }
        return current;
    }

}
