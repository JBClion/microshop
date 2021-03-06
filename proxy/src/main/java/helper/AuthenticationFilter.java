package helper;

import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by Jean-Baptiste Clion on 01.06.2016.
 * Filter managing proxy authentication to the application proxy.
 *
 * @author Jean-Baptiste Clion - jbclion@gmail.com
 * @version 0.1
 */
public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(UserManager.getCurrentUser() == null){
            httpServletResponse.sendRedirect(UserServiceFactory.getUserService().createLoginURL("http://localhost:8080"));
            return;
        }

        filterChain.doFilter(request, httpServletResponse);

    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {}

}