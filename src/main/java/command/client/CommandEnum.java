package command.client;

import command.ActionCommand;
import command.commands.LoginCommand;
import command.commands.LogoutCommand;

public enum CommandEnum {
    LOGIN{
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
