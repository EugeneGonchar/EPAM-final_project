package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;

import java.net.URI;
import java.net.URISyntaxException;

public class LanguageCommand implements ActionCommand {
    private static final String QUESTION_MARK = "?";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;
        String language = sessionRequestContent.getRequestParameter("lang");
        sessionRequestContent.add2SessionAttributes("local", language);

        /*try {
            URI uri = new URI(sessionRequestContent.getRequestHeader("referer"));
            page = uri.getPath() + QUESTION_MARK + uri.getQuery();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        page = ConfigurationManager.getProperty("path.page.main");
        actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        System.out.println(page);
        return actionPageContainer;
    }
}
