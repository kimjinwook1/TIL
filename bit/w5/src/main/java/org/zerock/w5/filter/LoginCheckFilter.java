package org.zerock.w5.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/mypage222"})
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("LoginCheckFilter doFilter...........");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) request;

        if (req.getSession().getAttribute("userInfo") == null) {

            System.out.println("이 사용자는 로그인이 안된 사용자 입니다.");
            resp.sendRedirect("/login");

            return;
        }
        chain.doFilter(request, response);
    }
}
