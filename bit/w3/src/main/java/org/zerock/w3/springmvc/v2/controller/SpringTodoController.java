package org.zerock.w3.springmvc.v2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.TodoDTO;
import org.zerock.w3.servletmvc.service.TodoService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/todo")
@SessionAttributes("userInfo")
public class SpringTodoController {

    @GetMapping("/list")
    public String list(Model model, HttpSession session) {

        log.info("todoList........get");

        try {
            session.getAttribute("userInfo");
            model.addAttribute("dtoList", TodoService.INSTANCE.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/todo/list";
    }

    @GetMapping("/modify")
    public String getModify(@RequestParam("tno") Long tno,
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

    @PostMapping("/modify")
    public String postModify(@RequestParam(value = "tno", defaultValue = "-1L") Long tnoData,
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

    @GetMapping("/read/{tno}")
    public String read(@PathVariable(name = "tno") String tnoStr,
                        Model model) {

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

    @GetMapping("/register")
    public String getRegister(HttpSession session) {

        session.getAttribute("userInfo");
        return "/todo/register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam("title") String title,
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

    @PostMapping("/remove")
    public String remove(@RequestParam(value = "tno", defaultValue = "-1L") Long tno) {

        try {
            TodoService.INSTANCE.remove(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";
    }

}
