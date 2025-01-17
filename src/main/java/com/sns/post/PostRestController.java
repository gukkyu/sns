package com.sns.post;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/post")
@RestController
public class PostRestController {

    @Autowired
    private PostBO postBO;

    @PostMapping("/create")
    public Map<String, Object> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "content", required = false) String content,
            HttpSession session){
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId == null){
            result.put("code", 403);
            result.put("error_message", "로그인 세션 만료");
            return result;
        }

        PostEntity addPost = postBO.addPost(file, content, userId);

        if (addPost != null) {
            result.put("code", 200);
            result.put("result", "성공");
        } else {
            result.put("code", 404);
            result.put("error_message", "관리자에게 연락!");
        }
        return result;
    }
}
