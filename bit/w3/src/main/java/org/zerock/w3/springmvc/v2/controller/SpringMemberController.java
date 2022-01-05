package org.zerock.w3.springmvc.v2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.zerock.w3.servletmvc.dto.MemberDTO;
import org.zerock.w3.servletmvc.service.MemberService;
import org.zerock.w3.servletmvc.service.SignUpService;
import org.zerock.w3.servletmvc.service.TodoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/member")
public class SpringMemberController {

    @GetMapping("/login")
    public String getLogin() {
        log.info("login..............");
        return "/member/login";
    }

    @PostMapping("/login")
    public String postLogin(
            @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult,
            @RequestParam(value = "rememberme", required = false) boolean rememberme,
            HttpServletRequest request) {

        if (!StringUtils.hasText(memberDTO.getUserId())) {
            bindingResult.addError(new FieldError("memberDTO", "userId", "아이디는 필수입니다."));
        }

        if (!StringUtils.hasText(memberDTO.getUserPw())) {
            bindingResult.addError(new FieldError("memberDTO", "userPw", "비밀번호는 필수입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("memberDTO={}", memberDTO);
            return "/member/login";
        }

        try {
            log.info("memberDTO={}", memberDTO);
            memberDTO = MemberService.INSTANCE.get(memberDTO.getUserId(), memberDTO.getUserPw());
            if (memberDTO == null) {
                return "redirect:/member/login";
            }

            HttpSession session = request.getSession();

            if (rememberme) {
                session.setMaxInactiveInterval(60);
            }

            session.setAttribute("userInfo", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/todo/list";

    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("userInfo");
        session.invalidate(); //세션이 무효화 되고 만약 로그인을 다시 하게 되면 새로운 세션을 발급해준다.

        return "redirect:/member/login";
    }

    @GetMapping("/read")
    public String read(Model model, HttpSession session) throws Exception {
        log.info("todoList........get");

        MemberDTO userInfo = (MemberDTO) session.getAttribute("userInfo");
        MemberDTO memberDTO = MemberService.INSTANCE.getByUno(userInfo.getUno());

        try {
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/member/memberread";
    }

    @GetMapping("/modify")
    public String getModify(@ModelAttribute MemberDTO memberDTO, Model model) {

        try {
            memberDTO = MemberService.INSTANCE.getByUno(memberDTO.getUno());
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/member/membermodify";
    }

    @PostMapping("/modify")
    public String postModify(
            @ModelAttribute MemberDTO memberDTO, Model model) {

        memberDTO = MemberDTO.builder()
                .userPw(memberDTO.getUserPw())
                .username(memberDTO.getUsername())
                .uno(memberDTO.getUno())
                .build();

        try {
            TodoService.INSTANCE.updateWriter(memberDTO.getUsername(), memberDTO.getUno());
            MemberService.INSTANCE.update(memberDTO);
            model.addAttribute("userInfo", memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";
    }

    @GetMapping("/remove")
    public String getRemove(@ModelAttribute MemberDTO memberDTO, Model model) {

        try {
            memberDTO = MemberService.INSTANCE.getByUno(memberDTO.getUno());
            model.addAttribute("memberDTO", memberDTO);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "/member/memberremove";
    }

    @PostMapping("/remove")
    public String postRemove(@RequestParam("uno") int uno,
                             @RequestParam("checkPw") String checkPw) {

        MemberDTO memberDTO = null;
        try {
            memberDTO = MemberService.INSTANCE.getByUno(uno);

            if (checkPw.equals(memberDTO.getUserPw())) {
                TodoService.INSTANCE.removeByUno(uno);
                MemberService.INSTANCE.remove(uno);
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/read";

    }


    @GetMapping("/signup")
    public String getSignup() throws Exception {

        return "/member/signup";

    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute MemberDTO memberDTO, BindingResult bindingResult,
                             @RequestParam("checkPw") String checkPw) {

        if (!StringUtils.hasText(memberDTO.getUserId())) {
            bindingResult.addError(new FieldError("memberDTO", "userId", "아이디는 필수입니다."));
        }
        if (!StringUtils.hasText(memberDTO.getUserPw())) {
            bindingResult.addError(new FieldError("memberDTO", "userPw", "비밀번호는 필수입니다."));
        }
        if (!StringUtils.hasText(checkPw)) {
            bindingResult.addError(new FieldError("memberDTO", "checkPw", "비밀번호는 필수입니다."));

        }
        if (!StringUtils.hasText(memberDTO.getUsername())) {
            bindingResult.addError(new FieldError("memberDTO", "username", "이름은 필수입니다."));
        }
        if (bindingResult.hasErrors()) {
            log.info("memberDTO={}", memberDTO);
            return "/member/signup";
        }
        try {
            boolean idCheck = SignUpService.INSTANCE.checkDuplicate(memberDTO.getUserId());
            log.info("idCheck..........." + idCheck);
            if (idCheck) {
                return "redirect:/member/signup";
            }

            if (memberDTO.getUserPw().equals(checkPw)) {
                memberDTO = MemberDTO.builder()
                        .userId(memberDTO.getUserId())
                        .userPw(memberDTO.getUserPw())
                        .username(memberDTO.getUsername())
                        .build();

                SignUpService.INSTANCE.register(memberDTO);
                return "redirect:/member/login";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member/signup";
    }
}
