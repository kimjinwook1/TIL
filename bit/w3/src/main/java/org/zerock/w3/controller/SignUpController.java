package org.zerock.w3.controller;

import org.zerock.w3.dto.MemberDTO;
import org.zerock.w3.service.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            if (idCheck) {
                response.sendRedirect("/signup");
                return;
            }
            String userpw = request.getParameter("userpw");
            String username = request.getParameter("username");

            memberDTO = MemberDTO.builder()
                    .userid(userid)
                    .userpw(userpw)
                    .username(username)
                    .build();

            SignUpService.INSTANCE.register(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");

        }
    }
}
