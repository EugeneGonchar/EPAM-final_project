package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;

public class PaymentCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        sessionRequestContent.add2SessionAttributes(Constant.ORDER_PROCESS_STATUS, Constant.STATUS_READY_PAYMENT);
        page = ConfigurationManager.getProperty("path.page.confirmation");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);

        return actionPageContainer;
    }
}
