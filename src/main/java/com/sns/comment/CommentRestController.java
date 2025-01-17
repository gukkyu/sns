package com.sns.comment;

import com.sns.comment.bo.CommentBO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

    @Autowired
    private CommentBO commentBO;

    @PostMapping("/create")
        public Map<String, Object> create(String comment, int postId, HttpSession session){
            Integer userId = (Integer)session.getAttribute("userId");
            Map<String, Object> result = new HashMap<>();
            if(userId == null){
                result.put("code", 403);
                return result;
            }

            int rowCount = commentBO.addCommentByPostIdAndUserId(postId, userId, comment);
            if(rowCount > 0){
                result.put("code", 200);
                result.put("result", "성공");
            } else{
                result.put("code", 404);
                result.put("error_message", "댓글쓰기 실패. 관리자에게 연락주세요.");
            }

            return result;

        }
}
