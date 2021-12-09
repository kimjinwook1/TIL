package org.zerock.w3.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.dto.TodoDTO;
import org.zerock.w3.service.TodoService;
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
@WebServlet(name = "todoModify", urlPatterns = "/todo/modify/*")
public class TodoModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Read.......................");

        String tnoStr = request.getPathInfo().substring(1);
        Long tno = StringUtil.parseLong(tnoStr, -1L);

        try {
            TodoDTO todoDTO = TodoService.INSTANCE.read(tno);
            request.setAttribute("dto", todoDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String viewPath = "/WEB-INF/todo/modify.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Long tno = StringUtil.parseLong(request.getParameter("tno"), -1L);
        String title = request.getParameter("title");
        LocalDate dueDate = StringUtil.parseLocalDate(request.getParameter("dueDate"));
        boolean finished = StringUtil.parseBoolean(request.getParameter("checkbox"));

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tno)
                .title(title)
                .dueDate(dueDate)
                .finished(finished)
                .build();

        try {
            TodoService.INSTANCE.update(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/todo/list");

    }
}
