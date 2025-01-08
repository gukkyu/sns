package com.sns.test;

import com.sns.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private PostMapper postMapper;

    @ResponseBody
    @GetMapping("/test1")
    public String test1(){
        return "<h1>test</h1>";
    }

    @ResponseBody
    @GetMapping("/test2")
    public Map<String,Object> test2(){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("사과", 8);
        map1.put("배", 14);
        map1.put("딸기", 81);
        return map1;
    }

    @GetMapping("/test3")
    public String test3(){
        return "test/test3";
    }

    @GetMapping("/test4")
    @ResponseBody
    public List<Map<String, Object>> test4(){
        return postMapper.selectPostListTest();
    }
}
