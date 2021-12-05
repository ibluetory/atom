package com.blue.atom;

import com.blue.atom.entity.User;
import com.blue.atom.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AtomApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    void contextLoads() {
        List<User> list = userService.list();
        System.out.println(list);
    }
}
