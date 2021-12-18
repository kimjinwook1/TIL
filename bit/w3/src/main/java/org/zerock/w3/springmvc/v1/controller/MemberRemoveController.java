package org.zerock.w3.springmvc.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.TodoService;

@Controller
//@RequestMapping("/member")
public class MemberRemoveController {

    @GetMapping("/remove")
    public String doGet(@RequestParam("uno") int uno, Model model) throws Exception {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "/member/memberremove";
    }

    @PostMapping("/remove")
    public String doPost(@RequestParam("uno") int uno,
                         @RequestParam("checkpw") String checkpw) throws Exception {

        MemberDTO memberDTO = null;
        try {
            memberDTO = MemberService.INSTANCE.getByUno(uno);

            if (checkpw.equals(memberDTO.getUserpw())) {
                TodoService.INSTANCE.removeByUno(uno);
                MemberService.INSTANCE.remove(uno);
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";

    }
}
