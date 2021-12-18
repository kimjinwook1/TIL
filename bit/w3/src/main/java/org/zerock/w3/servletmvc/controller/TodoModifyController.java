package org.zerock.w3.servletmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.MemberService;
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
//@WebServlet(name = "todoModify", urlPatterns = "/todo/modify/*")
public class TodoModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Read.......................");

        String tno1 = request.getParameter("tno");
//        String tnoStr = request.getPathInfo().substring(1);
        Long tno = StringUtil.parseLong(tno1, -1L);

        try {
            TodoDTO todoDTO = TodoService.INSTANCE.read(tno);
            request.setAttribute("dto", todoDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String viewPath = "/WEB-INF/todo/todomodify.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Long tnoData = StringUtil.parseLong(request.getParameter("tno"), -1L);
        String titleData = request.getParameter("title");
        LocalDate dueDateData = StringUtil.parseLocalDate(request.getParameter("dueDate"));
        boolean finishedData = StringUtil.parseBoolean(request.getParameter("checkbox"));

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tnoData)
                .title(titleData)
                .dueDate(dueDateData)
                .finished(finishedData)
                .build();
        try {
            TodoService.INSTANCE.update(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/todo/list");

    }
}
