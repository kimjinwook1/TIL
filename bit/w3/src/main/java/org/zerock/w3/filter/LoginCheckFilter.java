package org.zerock.w3.filter;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDTO;
import org.zerock.w3.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
@WebFilter(urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("doFilter..................");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session.getAttribute("userInfo") == null) {

            log.info("-----------세션에는 없고.. 쿠키를 이용하자..");
            //쿠키중에 remember-me 이름의 쿠키가 있는 지 검사

            boolean checkCookie = false;

            if (req.getCookies() != null && req.getCookies().length > 0) {

                Optional<Cookie> rememberCookie =
                        Arrays.stream(req.getCookies()).filter(ck -> ck.getName().equals("remember-me")).findFirst();

                String uuid = rememberCookie.get().getValue();

                try {
                    MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);
                    req.getSession().setAttribute("userInfo", memberDTO);
                    checkCookie = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //remember-me 쿠키의 값을 가지고 사용자의 정보를 가져와야
            //가지고 온 사용자의 정보를 현재 사용자의 HttpSession에 저장

            if (checkCookie) {
                resp.sendRedirect("/todo/list");
            } else {
                resp.sendRedirect("/login");

            }
            return;

        }

        chain.doFilter(request, response);
    }
}
