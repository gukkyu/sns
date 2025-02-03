package com.sns.post.bo;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class PostBO {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeBO likeBO;

    @Autowired
    private CommentBO commentBO;

    @Autowired
    private FileManagerService fileManager;

    public int getPostCountByUserId(int userId){
        return postRepository.countByUserId(userId);
    }

    public List<PostEntity> getPostByUserId(int userId){
        return postRepository.findByUserId(userId);
    }

    public List<PostEntity> getPost(){
        return postRepository.findAllByOrderByIdDesc();
    }

    public PostEntity addPost(MultipartFile file, String content, int userId){

        String imagePath = fileManager.uploadFile(file, userId);

        if(imagePath == null){
            return null;
        }

        return postRepository.save(PostEntity.builder()
                .userId(userId)
                .content(content)
                .imagePath(imagePath)
                .build());
    }

    public void deletePost(int postId){
        // transaction
        PostEntity post = postRepository.findById(postId).orElse(null);

        if(post == null){
            log.warn("[### 게시글 삭제 오류] postId: {}", postId);
        }
        String imagePath = post.getImagePath();
        fileManager.deleteFile(imagePath);
        postRepository.delete(post);

        likeBO.deleteAllLike(postId);
        commentBO.deleteAllComment(postId);
    }
}
