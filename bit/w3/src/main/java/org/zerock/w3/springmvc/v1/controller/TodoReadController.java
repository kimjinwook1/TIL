package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.TodoService;
import org.zerock.w3.util.StringUtil;

@Log4j2
@Controller
@RequestMapping("/todo/read/{tno}")
public class TodoReadController {

    @GetMapping
    public String doGet(@PathVariable(name = "tno") String tnoStr,
                        Model model) throws Exception {

        log.info("Read.......................");

        Long tno = StringUtil.parseLong(tnoStr, -1L);

        try {
            TodoDTO todoDTO = TodoService.INSTANCE.read(tno);
            model.addAttribute("dto", todoDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/todo/read";

    }
}
