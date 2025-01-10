package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/sign-up-view")
    public String signUpView(){
        return "user/sign-up-view";
    }

    @GetMapping("/sign-in-view")
    public String signInView(){
        return "user/sign-in-view";
    }

}
