package com.sns.user.bo;

import com.sns.common.EncryptUtils;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBO {

    @Autowired
    private UserRepository userRepository;
    public UserEntity getUserEntityByLoginId(String loginId){
        return userRepository.findByLoginId(loginId).orElse(null);
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
