package org.zerock.w3.springmvc.v2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @GetMapping("/modify/{tno}")
    public String getModify(@PathVariable Long tno, Model model) {

        log.info("Read.......................modify");

        try {
            TodoDTO todoDTO = TodoService.INSTANCE.read(tno);
            model.addAttribute("dto", todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/todo/todoModify";
    }

    @PostMapping("/modify/{tno}")
    public String postModify(@PathVariable Long tno, @ModelAttribute TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {

        log.info("todoDTO={}", todoDTO);

        if (!StringUtils.hasText(todoDTO.getTitle())) {
            bindingResult.addError(new FieldError("todoDTO", "title", "제목은 필수입니다."));
        }

        if (todoDTO.getDueDate() == null) {
            bindingResult.addError(new FieldError("todoDTO", "dueDate", "날짜는 필수입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            model.addAttribute("dto", todoDTO);
            return "/todo/todoModify";
        }

        try {
            TodoService.INSTANCE.update(todoDTO);
            redirectAttributes.addAttribute("tno", tno);
            redirectAttributes.addAttribute("modifyStatus", true);

            log.info("-------------------------");
            log.info("TodoDTO={}", todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/todo/read/{tno}";

    }

    @GetMapping("/read/{tno}")
    public String read(@PathVariable Long tno,
                       Model model) {

        log.info("Read.......................");

//        Long tno = StringUtil.parseLong(tnoStr, -1L);

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
    public String postRegisterV1(@ModelAttribute TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!StringUtils.hasText(todoDTO.getTitle())) {
            bindingResult.addError(new FieldError("todoDTO", "title", "제목은 필수입니다."));
        }

        if (todoDTO.getDueDate() == null) {
            bindingResult.addError(new FieldError("todoDTO", "dueDate", "날짜는 필수입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "todo/register";
        }

        log.info("--register doPost---------------------");
        log.info("TodoDTO={}", todoDTO);
        todoDTO = TodoDTO.builder()
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .writerid(todoDTO.getWriterid())
                .build();

        try {
            TodoService.INSTANCE.register(todoDTO);
            redirectAttributes.addAttribute("tno", TodoService.INSTANCE.readRecentTno());
            redirectAttributes.addAttribute("status", true);
            log.info("-------------------------");
            log.info("TodoDTO={}", todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/todo/read/{tno}";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute TodoDTO todoDTO) {

        try {
            TodoService.INSTANCE.remove(todoDTO.getTno());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";
    }

}
