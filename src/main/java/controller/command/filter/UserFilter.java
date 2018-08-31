package controller.command.filter;

import domain.entity.Role;
import resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Role role = (Role)request.getSession().getAttribute("role");

        if(role == null){
            response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));
        } else {
            switch (filterConfig.getFilterName()){
                case "AdminFilter":
                    if (role.getName().equals("Admin")) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));
                    }
                    break;
                default:
                    filterChain.doFilter(request, response);
                    break;
            }
        }
    }

    @Override
    public void destroy() {

    }
}

