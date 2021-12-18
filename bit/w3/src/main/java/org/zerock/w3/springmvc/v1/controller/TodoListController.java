package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.w3.servletmvc.service.TodoService;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/todo/list")
@SessionAttributes("userInfo")
public class TodoListController {

    @GetMapping
    public String doGet(Model model, HttpSession session) {

        log.info("todoList........get");

        try {
            session.getAttribute("userInfo");
            model.addAttribute("dtoList", TodoService.INSTANCE.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/todo/list";
    }
}
