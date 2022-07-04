package com.triple.mileage.api.service;

import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

//    @Autowired UserService userService;
//    @Autowired UserRepository userRepository;
//
//    @Test
//    public void joinTest() throws Exception {
//        //given
//        User user = new User();
//        user.setId(UUID.randomUUID());
//
//        //when
//        User user = userService.saveMember(user);
//
//        //then
//        assertEquals(user, userRepository.findOne(user.getId()));
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void validateDuplicateExceptionTest() throws Exception {
//        //given
//        UUID uuid = UUID.randomUUID();
//        UserDto.JoinReq dto1 = new UserDto.JoinReq(uuid);
//        UserDto.JoinReq dto2 = new UserDto.JoinReq(uuid);
//
//        //when
//        userService.saveMember(dto1);
//        userService.saveMember(dto2);
//
//        //then
//        fail("회원 중복 예외 발생");
//    }

}