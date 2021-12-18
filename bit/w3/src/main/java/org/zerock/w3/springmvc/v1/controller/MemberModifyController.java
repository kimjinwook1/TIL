package org.zerock.w3.springmvc.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.TodoService;

@Controller
@RequestMapping("/member")
@SessionAttributes("userInfo")
public class MemberModifyController {

    @GetMapping("/modify")
    public String doGet(@RequestParam("uno") int uno, Model model) throws Exception {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/member/membermodify";
    }

    @PostMapping("/modify")
    public String doPost(
            @RequestParam("userpw") String userpw,
            @RequestParam("username") String username,
            @RequestParam("uno") int uno,
            Model model
           ) throws Exception {

        MemberDTO memberDTO = MemberDTO.builder()
                .userpw(userpw)
                .username(username)
                .uno(uno)
                .build();

        try {
            TodoService.INSTANCE.updateWriter(username, uno);
            MemberService.INSTANCE.update(memberDTO);
            model.addAttribute("userInfo", memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";
    }
}
