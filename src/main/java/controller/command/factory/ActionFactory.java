package controller.command.factory;

import controller.command.ActionCommand;
import controller.command.EmptyCommand;
import controller.command.client.CommandEnum;
import controller.content.SessionRequestContent;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final String INPUT_NAME = "command";

    public ActionFactory(){

    }

    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(INPUT_NAME);

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException | NullPointerException e){
            System.out.println("no command");
            request.setAttribute("wrongAction", action +
                    MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }

}
