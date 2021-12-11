package org.zerock.w5.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/mypage"})
public class CookieCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("--------cookie check filter-----------");
        System.out.println("--------cookie check filter-----------");
        System.out.println("--------cookie check filter-----------");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie[] cks = req.getCookies();

        boolean result = false;

        if (cks != null && cks.length > 0) {
            for (Cookie ck : cks) {
                System.out.println(ck.getName() + ":  " + ck.getValue());
                if (ck.getName().equals("mycookie")) {
                    result = true;
                }
            }
        }

        if (result) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
