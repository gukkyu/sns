package com.sns.timeline.bo;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimelineBO {

    @Autowired
    private PostBO postBO;
    @Autowired
    private UserBO userBO;

    public List<CardDTO> generateCardList(){
        List<CardDTO> cardList = new ArrayList<>();


        List<PostEntity> postList = postBO.getPost();
        for(int i = 0; i < postList.size(); i++){
            PostEntity post = postList.get(i);
            int postUserId = post.getUserId();
            UserEntity user = userBO.getUserEntityById(postUserId);

            CardDTO card = new CardDTO();
            card.setPost(post);
            card.setUser(user);

            cardList.add(card);
        }

        return cardList;
    }
}
