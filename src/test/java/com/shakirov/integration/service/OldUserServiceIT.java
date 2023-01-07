package com.shakirov.integration.service;

import com.shakirov.database.pool.ConnectionPool;
import com.shakirov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;


//@IT
@RequiredArgsConstructor
public class OldUserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {
        System.out.println();
    }
}
