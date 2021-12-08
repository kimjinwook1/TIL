package org.zerock.w1.greetingcontroller.v2;

import org.zerock.w1.dto.SampleDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet(name = "greeting2", urlPatterns = "/greeting2")
public class GreetingControllerV2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SampleDTO dto = new SampleDTO();
        dto.setFirst("Gil Dong");
        dto.setLast("Hong");

        List<SampleDTO> list = IntStream.rangeClosed(1, 20).mapToObj(i -> {

            SampleDTO obj = new SampleDTO();
            obj.setFirst("Gil Dong" + i);
            obj.setLast("Hong" + i);

            return obj;

        }).collect(Collectors.toList());

        request.setAttribute("list", list);
        request.setAttribute("dto", dto);

        System.out.println("-----------------1");
        System.out.println(Thread.currentThread().getName());

        String viewPath = "/WEB-INF/1225.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost.................");

        response.sendRedirect("/hello2");

    }
}
