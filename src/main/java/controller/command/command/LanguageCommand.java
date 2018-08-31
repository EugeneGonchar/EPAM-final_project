package controller.command.command;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;

public class LanguageCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        String language = sessionRequestContent.getRequestParameter(Constant.LANG);
        sessionRequestContent.add2SessionAttributes(Constant.LOCAL, language);

        /*try {
            URI uri = new URI(sessionRequestContent.getRequestHeader("referer"));
            page = uri.getPath() + Constant.QUESTION_MARK + uri.getQuery();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        page = ConfigurationManager.getProperty("path.page.main");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        System.out.println(page);
        return actionPageContainer;
    }
}
