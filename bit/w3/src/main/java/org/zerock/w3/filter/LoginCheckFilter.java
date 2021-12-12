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
        log.info("doFilter..........................");

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();

        if (session.getAttribute("userInfo") == null) {
            log.info("-------------세션에는없고 쿠키를 이용해 봐야지..");
            //쿠키중에 rememver-me 이름의 쿠키가 있는지 검사
            boolean checkCookie = false;

            if (req.getCookies() != null && req.getCookies().length > 0) {
                Optional<Cookie> rememberCookie =
                        Arrays.stream(req.getCookies()).filter(ck -> ck.getName().equals(("remember-me"))).findFirst();

                String uuid = null;
                if (rememberCookie.isPresent()) {
                    // rememberCookie가 값이 있으면(즉, null이 아니면)
                    // isPresent()는 true를 반환하고 get()은 값을 반환한다.
                    uuid = rememberCookie.get().getValue();
                }
                try {
                    MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);
                    req.getSession().setAttribute("userInfo", memberDTO);
                    checkCookie = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //rememver-me 쿠키의 값을 가지고
                //사용자의 정보를 가지고 온 정보를 현재 사용자의 HttpSession에 저장
            }

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