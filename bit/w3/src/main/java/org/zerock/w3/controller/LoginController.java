package org.zerock.w3.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDTO;
import org.zerock.w3.service.MemberService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

@Log4j2
@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/login/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String userPw = request.getParameter("userpw");
        boolean rememberme = StringUtil.parseBoolean(request.getParameter("rememberme"));

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.get(userid, userPw);
            if (memberDTO == null) {
                response.sendRedirect("/login");
            }
            request.getSession().setAttribute("userInfo", memberDTO);

            if (rememberme) {

                String uuid = UUID.randomUUID().toString();

                //1week
                long endTime = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setPath("/"); //setPath("/") 모든 경로에 쿠키를 저장한다.
                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(rememberCookie);

                MemberService.INSTANCE.setCookieData(userid, uuid, new Date(endTime));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/todo/list");

    }
}
