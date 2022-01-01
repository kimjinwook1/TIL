package org.zerock.w3.servletmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "signup", urlPatterns = "/signup")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/signup/signup.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MemberDTO memberDTO = null;

        String userid = request.getParameter("userid");

        try {
            boolean idCheck = SignUpService.INSTANCE.checkDuplicate(userid);
            log.info("idCheck..........." + idCheck);
            if (idCheck) {
                response.sendRedirect("/signup");
                return;
            }
            String userpw = request.getParameter("userpw");
            String checkpw = request.getParameter("checkpw");
            String username = request.getParameter("username");

            if (userpw.equals(checkpw)) {
                memberDTO = MemberDTO.builder()
                        .userId(userid)
                        .userpw(userpw)
                        .username(username)
                        .build();

                SignUpService.INSTANCE.register(memberDTO);
                response.sendRedirect("/login");

            } else {
                response.sendRedirect("/signup");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
