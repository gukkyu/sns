package com.sns.comment.bo;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentBO {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserBO userBO;

    public int getCommentCount(int postId){
        return commentMapper.selectCommentCount(postId);
    }

    public List<Comment> getCommentList(){
        return commentMapper.selectCommentList();
    }

    public List<CommentDTO> generateCommentListByPostId(int postId){
        List<CommentDTO> commentDTOList = new ArrayList<>();

        List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
        for(int i = 0; i < commentList.size(); i++){
            CommentDTO commentDTO = new CommentDTO();
            UserEntity user = userBO.getUserEntityById(commentList.get(i).getUserId());

            commentDTO.setUser(user);
            commentDTO.setComment(commentList.get(i));
            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }

    public int addCommentByPostIdAndUserId(int postId, int userId, String comment){
        return commentMapper.insertCommentByPostIdAndUserId(postId, userId, comment);
    }

    public int deleteCommentById(int id){
        return commentMapper.deleteCommentById(id);
    }
    public void deleteAllComment(int postId){
        commentMapper.deleteAllComment(postId);
    }
}
