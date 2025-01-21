package com.sns.timeline.bo;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentDTO;
import com.sns.follower.bo.FollowerBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimelineBO {

    @Autowired
    private PostBO postBO;
    @Autowired
    private UserBO userBO;
    @Autowired
    private CommentBO commentBO;
    @Autowired
    private LikeBO likeBO;
    @Autowired
    private FollowerBO followerBO;

    public List<CardDTO> generateCardList(int userId){
        List<CardDTO> cardList = new ArrayList<>();

        List<PostEntity> postList = postBO.getPost();
        for(int i = 0; i < postList.size(); i++){
            PostEntity post = postList.get(i);
            int postUserId = post.getUserId();
            int postId = post.getId();
            UserEntity user = userBO.getUserEntityById(postUserId);
            int likeCount = likeBO.likeCount(postId);
            boolean isLike = likeBO.isLike(postId, userId);
            int commentCount = commentBO.getCommentCount(postId);
            boolean isFollow = followerBO.isFollow(postUserId, userId);

            List<CommentDTO> commentList = commentBO.generateCommentListByPostId(postId);

            CardDTO card = new CardDTO();
            card.setPost(post);
            card.setUser(user);
            card.setCommentList(commentList);
            card.setLikeCount(likeCount);
            card.setFilledLike(isLike);
            card.setCommentCount(commentCount);
            card.setFollow(isFollow);

            cardList.add(card);
        }

        return cardList;
    }
}
