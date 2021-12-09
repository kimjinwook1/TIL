package org.zerock.w3.controller;

import org.zerock.w3.service.TodoService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoRemove", urlPatterns = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long tno = StringUtil.parseLong(request.getParameter("tno"), -1L);

        try {
            TodoService.INSTANCE.remove(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/todo/list");
    }
}
