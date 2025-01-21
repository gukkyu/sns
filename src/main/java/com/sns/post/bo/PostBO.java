package com.sns.post.bo;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostBO {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileManagerService fileManager;

    public int getPostCountByUserId(int userId){
        return postRepository.countByUserId(userId);
    }

    public List<PostEntity> getPostByUserId(int userId){
        return postRepository.findByUserId(userId);
    }

    public List<PostEntity> getPost(){
        return postRepository.findAllByOrderByUpdatedAtDesc();
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
}
