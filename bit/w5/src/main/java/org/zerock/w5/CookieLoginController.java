package org.zerock.w5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "cookieLogin", urlPatterns = "/loginCookie")
public class CookieLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid");

        System.out.println("login.............");

        Cookie loginCookie = new Cookie("mycookie", userid);
//        loginCookie.setPath("/todo");
        loginCookie.setMaxAge(60 * 2);
        response.addCookie(loginCookie);

    }
}
