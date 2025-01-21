package com.sns.profile.bo;

import com.sns.follower.bo.FollowerBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.profile.domain.ProfileDTO;
import com.sns.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileBO {

    @Autowired
    private UserBO userBO;

    @Autowired
    private PostBO postBO;
    @Autowired
    private FollowerBO followerBO;

    public ProfileDTO generateProfile(int profileId, int userId){
        ProfileDTO profile = new ProfileDTO();
        profile.setUser(userBO.getUserEntityById(profileId));
        List<PostEntity> postList = postBO.getPostByUserId(profileId);
        profile.setPost(postList);
        profile.setPostCount(postBO.getPostCountByUserId(profileId));
        profile.setFollowerCount(followerBO.getFollowerCount(profileId));
        profile.setFollowingCount(followerBO.getFollowCount(profileId));
        profile.setFollow(followerBO.isFollow(profileId, userId));
        return profile;
    }
}
