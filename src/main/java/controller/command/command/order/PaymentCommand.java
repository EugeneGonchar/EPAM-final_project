package controller.command.command.order;

import controller.command.ActionCommand;
import controller.command.command.admin.GetAccidentsTableCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.ConfigurationManager;

public class PaymentCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(GetAccidentsTableCommand.class);

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
