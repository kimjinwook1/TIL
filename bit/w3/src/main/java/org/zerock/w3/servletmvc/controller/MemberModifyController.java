package org.zerock.w3.servletmvc.controller;

import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "memberModify", urlPatterns = "/member/modify/*")
public class MemberModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uno = Integer.parseInt(request.getParameter("uno"));
        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            request.setAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String viewPath = "/WEB-INF/member/membermodify.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userpw = request.getParameter("userpw");
        String username = request.getParameter("username");
        int uno = Integer.parseInt(request.getParameter("uno"));

        MemberDTO memberDTO = MemberDTO.builder()
                .userPw(userpw)
                .username(username)
                .uno(uno)
                .build();

        try {
            TodoService.INSTANCE.updateWriter(username, uno);
            MemberService.INSTANCE.update(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/member/read");
    }
}
