package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.TodoService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.http.HttpSession;

@Log4j2
//@Controller
//@RequestMapping("/todo/register")
//@SessionAttributes("userInfo")
public class TodoRegisterController2 {

    @GetMapping
    public String doGet(HttpSession session) {

        session.getAttribute("userInfo");
        return "/todo/register";
    }

    @PostMapping
    public String doPost(@RequestParam("title") String title,
                         @RequestParam("dueDate") String dueDate,
                         @RequestParam("writer") String writer,
                         @RequestParam("writerid") String writerId) {

        log.info("--register doPost---------------------");

        log.info(title);
        log.info(dueDate);

        TodoDTO todoDTO = TodoDTO.builder()
                .title(title)
                .writer(writer)
                .dueDate(StringUtil.parseLocalDate(dueDate))
                .writerid(Integer.parseInt(writerId))
                .build();

        try {
            TodoService.INSTANCE.register(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";
    }

}

