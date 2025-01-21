package com.sns.like.bo;

import com.sns.like.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeBO {

    @Autowired
    private LikeMapper likeMapper;

    public int likeCount(int postId){
        return likeMapper.likeCount(postId);
    }

    public boolean isLike(int postId, int userId){
        boolean isLike = likeMapper.isLike(postId, userId);
        return isLike;
    }

    public int like(int postId, int userId){
        boolean isLike = likeMapper.isLike(postId, userId);

        if(isLike){
            return likeMapper.deleteLike(postId, userId);
        } else{
            return likeMapper.insertLike(postId, userId);
        }
    }


}
