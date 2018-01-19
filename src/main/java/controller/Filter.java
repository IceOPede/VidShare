package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.html"})
public class Filter implements javax.servlet.Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();


        if (req.getRequestURI().endsWith("static/login.html")) {
            chain.doFilter(request, response);
        } else {
            String role = (String) session.getAttribute("userRole");
            if (role == null || role.equals("guest"))
                redirectLogin(response);
            else if (role.equals("student") &&
                    !req.getRequestURI().endsWith("subjectList.xhtml"))
                redirectLogin(response);
            else if (role.equals("teacher") &&
                    !req.getRequestURI().endsWith("static/Index.html"))
                redirectLogin(response);
            else
                chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private void redirectLogin(ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect("static/login.html");
    }
}
