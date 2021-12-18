package org.zerock.w3.servletmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.TodoService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
//@WebServlet(name = "todoRegister", urlPatterns = "/todo/register")
public class TodoRegisterController2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/todo/register.jsp";

        try {
            request.getSession().getAttribute("userInfo");
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("--register doPost---------------------");
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        LocalDate dueDate = StringUtil.parseLocalDate(request.getParameter("dueDate"));
        String writer = request.getParameter("writer");
        int writerId = Integer.parseInt(request.getParameter("writerid"));

        log.info(title);
        log.info(dueDate);

        TodoDTO todoDTO = TodoDTO.builder()
                .title(title)
                .writer(writer)
                .dueDate(dueDate)
                .writerid(writerId)
                .build();

        try {
            TodoService.INSTANCE.register(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/todo/list");
    }

}

