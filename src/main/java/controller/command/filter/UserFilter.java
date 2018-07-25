package controller.command.filter;

import controller.util.ConfigPage;
import resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/user/*" })
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute("user") != null) {
            filterChain.doFilter(request, response);
        } else{
            response.sendRedirect(ConfigurationManager.getProperty(ConfigPage.CONFIG_MAIN));
        }
    }

    @Override
    public void destroy() {

    }

}

