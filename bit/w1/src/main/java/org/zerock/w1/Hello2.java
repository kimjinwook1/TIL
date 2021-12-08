package org.zerock.w1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello2", urlPatterns = "/hello2")
public class Hello2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();//자동으로 close 됨
        out.println("<html>\n" +
                "<body>\n" +
                "<h1>" + "Hello2 Hello2" + "</h1>\n" +
                "</body>\n" +

                "</html>");
    }

//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.setCharacterEncoding("utf-8");
//        // Hello
//        PrintWriter out = response.getWriter();//자동으로 close 됨
//        out.println("<html>\n" +
//                "<body>\n" +
//                "<h1>" + "Hello2 Hello2" + "</h1>\n" +
//                "</body>\n" +
//                "</html>");
//    }
}
