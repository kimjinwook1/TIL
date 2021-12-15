package org.zerock.w3.servletmvc.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "timeController", value = "/time")
@Log4j2
public class TimeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("TimeController doGet.............");
        LocalDateTime now = LocalDateTime.now();

        request.setAttribute("now", now);

        String viewPath = "/WEB-INF/time.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
