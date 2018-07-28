package controller.command.client;

import controller.command.ActionCommand;
import controller.command.command.*;
import controller.command.command.user.*;

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
    },
    SIGNUP{
        {
            this.command = new SignupCommand();
        }
    },
    CHANGE_LOGIN{
        {
            this.command = new ChangeLoginCommand();
        }
    },
    CHANGE_PASSWORD{
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_NAME_SURNAME{
        {
            this.command = new ChangeNameSurnameCommand();
        }
    },
    CHANGE_EMAIL{
        {
            this.command = new ChangeEmailCommand();
        }
    },
    CHANGE_PHONE{
        {
            this.command = new ChangePhoneCommand();
        }
    },
    GET_LOCATIONS{
        {
            this.command = new GetLocationsCommand();
        }
    },
    GET_CARS{
        {
            this.command = new GetCarsCommand();
        }
    },
    CHOOSE_DATE_ADDRESS_OF_ORDER{
        {
            this.command = new ChooseDateAndAddress();
        }
    },
    GO_TO_DRIVER_DETAILS{
        {
            this.command = new GetDriverDetailsPageCommand();
        }
    },
    DRIVER_DETAILS{
        {
            this.command = new DriverDetailsCommand();
        }
    },
    PAYMENT{
        {
            this.command = new PaymentCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
