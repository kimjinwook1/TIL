package org.zerock.w5;

import org.zerock.w5.dto.MemberDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/login.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //있었으면 그냥 가져오고 없으면 새로 만들어서 보내주자.
        HttpSession session = request.getSession();

        String userid = request.getParameter("userid");
        System.out.println("userid = " + userid);

        MemberDTO memberDTO = new MemberDTO(userid, "1111", userid + "님");

        session.setAttribute("userInfo", memberDTO);
        response.sendRedirect("/mypage");

    }
}
