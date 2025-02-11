package com.sns.follower;

import com.sns.follower.bo.FollowerBO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FollowerRestController {

    @Autowired
    private FollowerBO followerBO;

    @GetMapping("/follow")
    public Map<String, Object> followToggle(
            @RequestParam("postUserId") int postUserId,
            HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        if(userId == null){
            result.put("code", 403);
            result.put("error_message", "로그인 세션이 만료되었습니다. 다시 로그인 해주세요.");
            return result;
        }

        int rowCount = followerBO.follow(postUserId, userId);

        if(rowCount > 0){
            result.put("code", 200);
            result.put("result", "성공");
        } else{
            result.put("code", 404);
            result.put("error_message", "실패! 관리자에게 연락주세요.");
        }
        return result;
    }

}
