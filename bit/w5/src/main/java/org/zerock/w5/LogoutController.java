package org.zerock.w5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.getSession().removeAttribute("userInfo");
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        session.invalidate(); //세션이 무효화 되고 만약 로그인을 다시 하게 되면 새로운 세션을 발급해준다.

        response.sendRedirect("/sample");
    }
}
