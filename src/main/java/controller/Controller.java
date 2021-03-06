package controller;

import controller.command.ActionCommand;
import controller.command.factory.ActionFactory;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.Car;
import resource.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Controller extends HttpServlet {

    private final static int SESSION_LIFE_TIME_IN_SEC = -1;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String page = null;
        request.getSession().setMaxInactiveInterval(SESSION_LIFE_TIME_IN_SEC);
        List<Car> cars = new LinkedList<>();

        ActionFactory client = new ActionFactory();

        SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
        sessionRequestContent.extractValues();

        ActionCommand command = client.defineCommand(sessionRequestContent);

        ActionPageContainer actionPageContainer = null;
        actionPageContainer = command.execute(sessionRequestContent);
        page = actionPageContainer.getPage();

        sessionRequestContent.insertReqSesAttributes();

        request = sessionRequestContent.getRequest();

        if(actionPageContainer.getUrlAction() == URLAction.REDIRECT && page != null){
            response.sendRedirect(page);
        } else if(actionPageContainer.getUrlAction() == URLAction.FORWARD && page != null){
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else{
            page = ConfigurationManager.getProperty("path.page.error");
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy(){

    }
}