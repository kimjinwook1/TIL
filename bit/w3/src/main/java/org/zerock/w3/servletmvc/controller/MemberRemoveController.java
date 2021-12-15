package org.zerock.w3.servletmvc.controller;

import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "memberRemove", urlPatterns = "/member/remove")
public class MemberRemoveController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uno = Integer.parseInt(request.getParameter("uno"));
        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            request.setAttribute("memberDTO", memberDTO);

        } catch (Exception e) {

            e.printStackTrace();
        }
        String viewPath = "/WEB-INF/member/memberremove.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uno = Integer.parseInt(request.getParameter("uno"));
        MemberDTO memberDTO = null;
        try {
            memberDTO = MemberService.INSTANCE.getByUno(uno);

            String checkpw = request.getParameter("checkpw");
            if (checkpw.equals(memberDTO.getUserpw())) {
                TodoService.INSTANCE.removeByUno(uno);
                MemberService.INSTANCE.remove(uno);
                response.sendRedirect("/login");
            } else {
                response.sendRedirect("/member/read");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
