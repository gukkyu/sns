package com.sns.follower.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowerMapper {
    public boolean isFollow(@Param("postId") int postId, @Param("userId") int userId);
    public int followCount(int userId);
    public int followerCount(int userId);
    public int insertFollow(@Param("postId") int postId, @Param("userId") int userId);
    public int deleteFollow(@Param("postId") int postId, @Param("userId") int userId);

}
