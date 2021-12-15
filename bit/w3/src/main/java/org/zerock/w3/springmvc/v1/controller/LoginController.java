package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.util.StringUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

@Log4j2
@Controller
@SessionAttributes("userInfo")
public class LoginController extends HttpServlet {

    @GetMapping("/login")
    public String doGet() {
        return "/login/login";
    }

    @PostMapping("/login")
    public String doPost(@RequestParam("userid")String userid,
                         @RequestParam("userpw") String userPw,
                         @RequestParam("rememberme") boolean rememberme,
                         Model model,
                         HttpServletResponse response) throws Exception {


        try {
            MemberDTO memberDTO = MemberService.INSTANCE.get(userid, userPw);
            if (memberDTO == null) {
                return "redirect:/signup";
            }

           model.addAttribute("userInfo", memberDTO);
            if (rememberme) {

                String uuid = UUID.randomUUID().toString();

                //1week
                long endTime = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setPath("/"); //setPath("/") 모든 경로에 쿠키를 저장한다.
                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(rememberCookie);

                MemberService.INSTANCE.setCookieData(userid, uuid, new Date(endTime));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";

    }
}
