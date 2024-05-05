package com.dhy.oom.demos.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @InjectMocks
    private UserService userService;
    @Mock
    private IdService idService;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试添加用户的方法。
     * 该方法首先会设置模拟对象的行为，然后调用待测试的添加用户方法，最后验证相关方法是否被正确调用。
     * 注意：该测试着重验证功能逻辑，不涉及对非功能性行为（如打印输出）的测试。
     */
    @Test
    void testAddUser() {
        // 安排：准备测试数据和模拟对象的行为
        String expectedId = "123";
        String name = "John";
        int age = 30;
        when(idService.getId()).thenReturn(expectedId); // 模拟ID服务返回预期的用户ID

        // 行动：调用待测试的方法
        userService.addUser(name, age); // 调用添加用户方法，传入准备好的姓名和年龄

        // 断言：验证模拟对象的方法是否被正确调用了一次
        verify(idService, times(1)).getId(); // 验证ID服务的getId方法是否被调用了一次
    }

}
