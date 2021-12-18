package org.zerock.w3.springmvc.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;

@Controller
//@RequestMapping("/member")
//@SessionAttributes("userInfo")
public class MemberReadController {

    @GetMapping("/read")
    public String doGet(@ModelAttribute("userInfo") MemberDTO memberDTOParameter,
                        Model model) throws Exception {

        int uno = memberDTOParameter.getUno();

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getByUno(uno);
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/member/memberread";

    }
}
