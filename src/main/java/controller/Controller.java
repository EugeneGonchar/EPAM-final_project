package controller;

import controller.command.ActionCommand;
import controller.command.factory.ActionFactory;
import controller.content.SessionRequestContent;
import dao.connection.ConnectionPool;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static int SESSION_LIFE_TIME_IN_SEC = 10;

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);/*оставить пустым*/
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String page = null;

        request.getSession().setMaxInactiveInterval(SESSION_LIFE_TIME_IN_SEC);

        ActionFactory client = new ActionFactory();

        SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
        sessionRequestContent.extractValues();

        ActionCommand command = client.defineCommand(sessionRequestContent);

        page = command.execute(sessionRequestContent);

        sessionRequestContent.insertReqSesAttributes();

        request = sessionRequestContent.getRequest();

        if(page != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        else{
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();
        connectionPool.deregisterAllDrivers();
        super.destroy();
    }
}