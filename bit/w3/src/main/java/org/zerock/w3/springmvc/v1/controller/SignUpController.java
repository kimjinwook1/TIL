package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {

    public String doGet() throws Exception {

        return "/signup/signup";

    }

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
