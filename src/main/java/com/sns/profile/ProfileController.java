package com.sns.profile;

import com.sns.profile.bo.ProfileBO;
import com.sns.profile.domain.ProfileDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private ProfileBO profileBO;

    @GetMapping("/profile")
    public String profile(@RequestParam("userId") int profileId, HttpSession session, Model model){
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId == null){
            return "/user/sign-in-view";
        }
        ProfileDTO profileDTO = profileBO.generateProfile(profileId, userId);
        model.addAttribute("profile", profileDTO);
        return "profile/profile";
    }
}
