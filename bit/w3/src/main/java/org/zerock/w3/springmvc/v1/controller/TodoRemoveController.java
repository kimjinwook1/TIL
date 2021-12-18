package org.zerock.w3.springmvc.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.w3.servletmvc.service.TodoService;

//@Controller
//@RequestMapping("/todo/remove")
public class TodoRemoveController {

    @PostMapping
    public String doPost(@RequestParam(value = "tno", defaultValue = "-1L") Long tno) {

        try {
            TodoService.INSTANCE.remove(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";
    }
}
