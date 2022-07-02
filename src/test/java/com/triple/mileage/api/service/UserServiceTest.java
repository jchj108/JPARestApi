package com.triple.mileage.api.service;

import com.triple.mileage.api.entity.User;
import com.triple.mileage.api.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired EntityManager em;

    @Test
    public void joinTest() throws Exception {
        //given
        User user = new User();
        user.setId(UUID.randomUUID());

        //when
        UUID savedId = userService.join(user);

        //then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void validateDuplicateExceptionTest() throws Exception {
        //given

        //when

        //then

    }

}