package com.sns.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/sign-up-view")
    public String signUpView(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/timeline";
        }
        return "user/sign-up-view";
    }

    @GetMapping("/sign-in-view")
    public String signInView(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/timeline";
        }
        return "user/sign-in-view";
    }

}
