package com.sns.profile.domain;

import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDTO {
    private List<PostEntity> post;
    private UserEntity user;
    private int postCount;
    private int followerCount;
    private int followingCount;
    private boolean isFollow;
}
