package com.sns.follower.bo;

import com.sns.follower.mapper.FollowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerBO {

    @Autowired
    private FollowerMapper followerMapper;

    public boolean isFollow(int postId, int userId){
        boolean isFollow = followerMapper.isFollow(postId, userId);
        return isFollow;
    }

    public int follow(int postId, int userId){
        boolean isFollow = followerMapper.isFollow(postId, userId);

        if(isFollow){
            return followerMapper.deleteFollow(postId, userId);
        } else{
            return followerMapper.insertFollow(postId, userId);
        }
    }
}
