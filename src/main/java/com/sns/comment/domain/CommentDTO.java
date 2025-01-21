package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Comment comment;

    private UserEntity user;
}
