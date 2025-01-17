package com.sns.user;

import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserBO userBO;

    @GetMapping("/is-duplicate-id")
    public Map<String, Object> isDuplicateId(
            @RequestParam("loginId") String loginId
    ){
        UserEntity user = userBO.getUserEntityByLoginId(loginId);

        Map<String, Object> result = new HashMap<>();
        boolean isDuplicate = false;
        if(user != null){
            isDuplicate = true;
        }

        result.put("code", 200);
        result.put("is_duplicate_id", isDuplicate);

        return result;
    }

    @PostMapping("/sign-up")
    public Map<String, Object> signUp(
            @RequestParam("id") String loginId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ){
        boolean isSuccess = userBO.addUser(loginId, password, name, email);

        Map<String, Object> result = new HashMap<>();
        if(isSuccess){
            result.put("code", 200);
            result.put("result", "성공");
        } else{
            result.put("code", 500);
            result.put("error_message", "회원가입에 실패했습니다.");
        }
        return result;
    }

    @PostMapping("/sign-in")
    public Map<String, Object> signIn(
            @RequestParam("id") String loginId,
            @RequestParam("password") String password,
            HttpSession session
    ){
        UserEntity user = userBO.getUserByLoginIdAndPassword(loginId, password);
        Map<String, Object> result = new HashMap<>();

        if(user != null){
            result.put("code", 200);
            result.put("result", "성공");
            session.setAttribute("userLoginId", user.getLoginId());
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
        } else{
            result.put("error_message", "아이디와 비밀번호를 확인해주세요.");
        }
        return result;
    }
}
