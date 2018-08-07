package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import entity.Order;
import entity.User;
import resource.ConfigurationManager;

public class PaymentCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        Order order = (Order)sessionRequestContent.getSessionAttribute("order");
        User user = (User)sessionRequestContent.getSessionAttribute("user");

        if(user != null){
            order.setUserId(user.getId());
        } else{
            //вставка айди гостя
        }

        page = ConfigurationManager.getProperty("path.page.confirmation");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);

        return actionPageContainer;
    }
}
