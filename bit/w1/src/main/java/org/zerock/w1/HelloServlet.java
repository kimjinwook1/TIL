package org.zerock.w1;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html>\n" +
                "<body>\n" +
                "<h1>" + this + "</h1>\n" +
                "<h1>" + Thread.currentThread().getName() + "</h1>\n" +
                "<h1>" + message + "</h1>\n" +
                "</body>\n" +
                "</html>");
    }

    public void destroy() {
    }
}