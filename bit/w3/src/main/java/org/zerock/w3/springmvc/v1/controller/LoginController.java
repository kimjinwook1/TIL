package org.zerock.w3.springmvc.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.UUID;

@Log4j2
//@Controller
//@SessionAttributes("userInfo")
//@RequestMapping("/login")
public class LoginController extends HttpServlet {

    @GetMapping
    public String doGet() {
        log.info("login..............");
        return "/login/login";
    }

    @PostMapping
    public String doPost(
            @RequestParam("userid") String userid,
            @RequestParam("userpw") String userPw,
            @RequestParam(value = "rememberme", required = false) boolean rememberme,
            @CookieValue(value = "remember-me", required = false) Cookie rememberCookie,
            HttpServletResponse response,
            Model model) throws Exception {

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.get(userid, userPw);
            if (memberDTO == null) {
                return "redirect:/login";
            }
            model.addAttribute("userInfo", memberDTO);
            if (rememberme) {

                String uuid = UUID.randomUUID().toString();

                //1week
                long endTime = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7);

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
