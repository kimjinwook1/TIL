package org.zerock.w3.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.MemberDTO;
import org.zerock.w3.service.MemberService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid");
        boolean rememberme = StringUtil.parseBoolean(request.getParameter("rememberme"));

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.get(userid);

            request.getSession().setAttribute("userInfo", memberDTO);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
