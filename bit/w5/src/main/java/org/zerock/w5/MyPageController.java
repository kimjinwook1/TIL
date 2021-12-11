package org.zerock.w5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "myPage", urlPatterns = "/mypage")
public class MyPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //LoginCheckFilter에서 처리해줒ㅁ
//        if (session.getAttribute("userInfo") == null) {
//            response.sendRedirect("/login");
//            return;
//        }

        System.out.println(session.getAttribute("userInfo"));

        String viewPath = "/WEB-INF/mypage.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
