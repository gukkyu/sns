package com.sns.user.bo;

import com.sns.common.EncryptUtils;
import com.sns.common.FileManagerService;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserBO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileManagerService fileManagerService;

    public UserEntity getUserEntityById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity getUserEntityByLoginId(String loginId){
        return userRepository.findByLoginId(loginId).orElse(null);
    }

    public void updateUserIntroduceAndImagePathById(int userId, String introduce, MultipartFile file){
        UserEntity userEntity = getUserEntityById(userId);

        if(userEntity != null){
            String imagePath = null;
            if(!file.isEmpty()){
                imagePath = fileManagerService.uploadFile(file, userId);

                if(imagePath != null && userEntity.getProfileImagePath() != null){
                    fileManagerService.deleteFile(userEntity.getProfileImagePath());
                }

            }
            if(introduce.isEmpty()){
                introduce = null;
            }
            userEntity = userEntity.toBuilder().profileImagePath(imagePath).introduce(introduce).build();

            userRepository.save(userEntity);
        }
    }

    public boolean addUser(String loginId, String password, String name, String email){
        String hashedPassword = EncryptUtils.md5(password);
        UserEntity user = userRepository.save(
                UserEntity.builder()
                        .loginId(loginId)
                        .password(hashedPassword)
                        .name(name)
                        .email(email)
                        .build()
        );

        return user == null? false : true;
    }

    public UserEntity getUserByLoginIdAndPassword(String loginId, String password){
        String hashedPassword = EncryptUtils.md5(password);
        return userRepository.findByLoginIdAndPassword(loginId, hashedPassword).orElse(null);
    }
}
