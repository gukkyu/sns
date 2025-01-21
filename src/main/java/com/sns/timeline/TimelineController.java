package com.sns.timeline;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TimelineController {

    @Autowired
    private PostBO postBO;

    @Autowired
    private CommentBO commentBO;

    @Autowired
    private TimelineBO timelineBO;

    @GetMapping("/timeline")
    public String timeline(Model model, HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId == null){
            return "/user/sign-in-view";
        }
        List<CardDTO> cardList = timelineBO.generateCardList(userId);
        model.addAttribute("cardList", cardList);


//        List<PostEntity> postList = postBO.getPost();
//        List<Comment> commentList = commentBO.getCommentList();
//        model.addAttribute("postList", postList);
//        model.addAttribute("commentList", commentList);
        return "timeline/timeline";
    }
}
