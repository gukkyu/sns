package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    public boolean isLike(@Param("postId") int postId, @Param("userId") int userId);
    public int likeCount(int postId);
    public int insertLike(@Param("postId") int postId, @Param("userId") int userId);
    public int deleteLike(@Param("postId") int postId, @Param("userId") int userId);
}
