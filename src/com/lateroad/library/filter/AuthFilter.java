package com.lateroad.library.filter;

import com.lateroad.library.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {

    private List<String> pathFilters = Arrays.asList(new String[]{"/home.html", "/home.jsp"});

    public AuthFilter() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        HttpSession session = ((HttpServletRequest) request).getSession();

        User user = (User)session.getAttribute("PRINCIPAL");
        if(user != null){
            if("/login.html".equals(uri) || "/signin.jsp".equals(uri)){
                ((HttpServletResponse)response).sendRedirect("/index.html");
                return;
            }
            else{
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (!pathFilters.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

//?????
        ((HttpServletResponse)response).sendRedirect("login.html?loginorpassword=invalid");

    }

    public void destroy() {

    }
}
