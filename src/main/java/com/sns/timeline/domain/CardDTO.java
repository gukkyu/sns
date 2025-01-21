package com.sns.timeline.domain;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CardDTO {
    // 글 1개
    private PostEntity post;

    // 글쓴이 정보
    private UserEntity user;

    // 댓글 N개
    private List<CommentDTO> commentList;

    private int commentCount;
    // 좋아요 N개
    private int likeCount;

    private boolean filledLike;

    private boolean isFollow;
}
