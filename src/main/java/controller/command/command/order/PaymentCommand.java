package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.OrderProcessStatusConstant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Order;
import entity.User;
import resource.ConfigurationManager;
import service.OrderService;
import service.ServiceFactory;

public class PaymentCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        sessionRequestContent.add2SessionAttributes("orderProcessStatus", OrderProcessStatusConstant.STATUS_READY_PAYMENT);
        page = ConfigurationManager.getProperty("path.page.confirmation");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
