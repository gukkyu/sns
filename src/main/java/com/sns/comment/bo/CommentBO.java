package com.sns.comment.bo;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentBO {

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentList(){
        return commentMapper.selectCommentList();
    }

    public int addCommentByPostIdAndUserId(int postId, int userId, String comment){
        return commentMapper.insertCommentByPostIdAndUserId(postId, userId, comment);
    }
}
