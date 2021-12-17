package org.zerock.w3.springmvc.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@SessionAttributes("userInfo")
@Controller
@RequestMapping
public class LogoutController {

    @PostMapping("/logout")
    public String doPost(HttpSession session) throws Exception {

        session.removeAttribute("userInfo");
        session.invalidate(); //세션이 무효화 되고 만약 로그인을 다시 하게 되면 새로운 세션을 발급해준다.

      return  "redirect:/login";
    }
}
