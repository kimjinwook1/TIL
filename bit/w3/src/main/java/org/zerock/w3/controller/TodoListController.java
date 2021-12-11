package org.zerock.w3.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.domain.MemberVO;
import org.zerock.w3.service.MemberService;
import org.zerock.w3.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "todoList", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("todoList........get");

        String viewPath = "/WEB-INF/todo/list.jsp";

        try {
            request.setAttribute("dtoList", TodoService.INSTANCE.getAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
