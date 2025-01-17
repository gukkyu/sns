package com.sns.comment.mapper;

import com.sns.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public List<Comment> selectCommentList();

    public int insertCommentByPostIdAndUserId(
            @Param("postId") int postId,
            @Param("userId") int userId,
            @Param("comment") String comment
            );

}
