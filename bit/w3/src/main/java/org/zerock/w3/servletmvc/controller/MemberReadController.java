package org.zerock.w3.servletmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
//@WebServlet(name = "memberRead", urlPatterns = "/member/read/*")
public class MemberReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MemberDTO memberDTOParameter = (MemberDTO) request.getSession().getAttribute("userInfo");
        int uno = memberDTOParameter.getUno();

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            request.setAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
            String viewPath = "/WEB-INF/member/memberread.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
            dispatcher.forward(request, response);

    }
}
