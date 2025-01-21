package com.sns.follower.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Follower {
    private int userId;
    private int followerId;
    private LocalDateTime createdAt;
}
