package com.triple.mileage.repository;

import com.triple.api.mileage.entity.User;
import com.triple.api.mileage.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUser() throws Exception {
        //given
        User user = new User();

        //when
        UUID saveId = userRepository.save(user);
        User findUser = userRepository.findById(saveId);

        //then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
    }
}