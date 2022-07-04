package com.triple.mileage.repository;

import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void userSaveTest() throws Exception {
        //given
//        User user = new User();
//        user.setId(UUID.randomUUID());
//
//        //when
//        UUID saveId = userRepository.save(user);
//        User findUser = userRepository.findOne(saveId);
//
//        //then
//        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
    }
}