package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.TodoService;
import org.zerock.w3.util.StringUtil;

@Log4j2
@Controller
@RequestMapping("/todo/modify")
public class TodoModifyController {

    @GetMapping
    public String doGet(@RequestParam("tno") Long tno,
                        Model model) {

        log.info("Read.......................");

        try {
            TodoDTO todoDTO = TodoService.INSTANCE.read(tno);
            model.addAttribute("dto", todoDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/todo/todomodify";
    }

    @PostMapping
    public String doPost(@RequestParam(value = "tno", defaultValue = "-1L") Long tnoData,
                         @RequestParam("title") String titleData,
                         @RequestParam("dueDate") String dueDateData,
                         @RequestParam(value = "checkbox", required = false) boolean finishedData) {

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tnoData)
                .title(titleData)
                .dueDate(StringUtil.parseLocalDate(dueDateData))
                .finished(finishedData)
                .build();

        try {
            TodoService.INSTANCE.update(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/todo/list";

    }

}
