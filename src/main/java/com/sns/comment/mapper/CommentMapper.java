package com.sns.comment.mapper;

import com.sns.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int selectCommentCount(int postId);

    public List<Comment> selectCommentList();

    public List<Comment> selectCommentListByPostId(int postId);

    public int insertCommentByPostIdAndUserId(
            @Param("postId") int postId,
            @Param("userId") int userId,
            @Param("comment") String comment
    );

    public int deleteCommentById(int id);

    public void deleteAllComment(int postId);
}
