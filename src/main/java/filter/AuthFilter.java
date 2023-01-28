package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/info"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter worked");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession();
        String userAccount = (String) session.getAttribute("email");
        if(userAccount != null){
            filterChain.doFilter(httpRequest,httpResponse);
            System.out.println("Go to user info page");
        } else{
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
            System.out.println("Send back to login page");
        }
    }

}
