package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Address;
import resource.ConfigurationManager;
import service.AddressService;
import service.ServiceFactory;

import java.util.List;

public class GetLocationsCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Address> addressList = null;

        AddressService addressService = ServiceFactory.getAddressService();

        addressList = addressService.getAddressList();

        sessionRequestContent.add2RequestAttributes("addressList", addressList);
        page = ConfigurationManager.getProperty("path.page.rent");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
