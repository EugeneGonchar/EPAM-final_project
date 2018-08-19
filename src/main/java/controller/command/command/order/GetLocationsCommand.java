package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.Address;
import resource.ConfigurationManager;
import service.AddressService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import java.util.List;

public class GetLocationsCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent){
        ActionPageContainer actionPageContainer = null;
        String page = null;
        List<Address> addressList = null;

        AddressService addressService = ServiceFactory.getInstance().getAddressService();

        try {
            addressList = addressService.getAddressList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        sessionRequestContent.add2RequestAttributes(Constant.ADDRESS_LIST, addressList);
        page = ConfigurationManager.getProperty("path.page.rent");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
