package com.sns.like;

import com.sns.like.bo.LikeBO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LikeRestController {

    @Autowired
    private LikeBO likeBO;
    // like?postId=13       @RequestParam("postId")
    // /like/13             @PathVariable(name = "postId")
    @GetMapping("/like")
    public Map<String, Object> likeToggle(
            @RequestParam("postId") int postId,
            HttpSession session
    ){
        Integer userId = (Integer)session.getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        if(userId == null){
            result.put("code", 403);
            result.put("error_message", "로그인 세션이 만료되었습니다. 다시 로그인 해주세요.");
            return result;
        }

        int rowCount = likeBO.like(postId, userId);

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
