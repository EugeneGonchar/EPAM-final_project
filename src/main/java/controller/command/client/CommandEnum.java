package controller.command.client;

import controller.command.ActionCommand;
import controller.command.command.*;
import controller.command.command.admin.*;
import controller.command.command.order.*;
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
            this.command = new ChooseDateAndAddressCommand();
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
    },
    GET_ORDERS{
        {
            this.command = new GetOrdersCommand();
        }
    },
    ADD_ORDER{
        {
            this.command = new AddOrderCommand();
        }
    },
    LANGUAGE{
        {
            this.command = new LanguageCommand();
        }
    },
    USERS_TABLE{
        {
            this.command = new GetUsersTableCommand();
        }
    },
    CARS_TABLE{
        {
            this.command = new GetCarsTableCommand();
        }
    },
    ORDERS_TABLE{
        {
            this.command = new GetOrdersTableCommand();
        }
    },
    UPLOAD_USER_IMG{
        {
            this.command = new UploadUserImageCommand();
        }
    },
    UPLOAD_CAR_IMG{
        {
            this.command = new UploadCarImageCommand();
        }
    },
    UPDATE_STATUS{
        {
            this.command = new UpdateStatusCommand();
        }
    },
    ACCIDENTS_TABLE{
        {
            this.command = new GetAccidentsTableCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
