package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.SignUpService;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping
    public String doGet() throws Exception {

        return "/signup/signup";

    }

    @PostMapping
    public String doPost(@RequestParam("userid") String userid,
                         @RequestParam("userpw") String userpw,
                         @RequestParam("checkpw") String checkpw,
                         @RequestParam("username") String username) throws Exception {

        MemberDTO memberDTO = null;

        try {
            boolean idCheck = SignUpService.INSTANCE.checkDuplicate(userid);
            log.info("idCheck..........." + idCheck);
            if (idCheck) {
                return "redirect:/signup";
            }

            if (userpw.equals(checkpw)) {
                memberDTO = MemberDTO.builder()
                        .userid(userid)
                        .userpw(userpw)
                        .username(username)
                        .build();

                SignUpService.INSTANCE.register(memberDTO);
                return "redirect:/login";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/signup";
    }
}
