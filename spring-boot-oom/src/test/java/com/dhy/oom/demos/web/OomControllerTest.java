package com.dhy.oom.demos.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OomController.class)
public class OomControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private OomController oomController = new OomController();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OomController()).build();
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(get("/oom/remove"))
                .andExpect(status().isOk())
                .andExpect(content().string("okl"));
    }

    @Test
    public void testAdd() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建MockMvc对象
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(oomController).build();

        // 执行请求
        mockMvc.perform(get("/oom/add").param("size", "2"))
                .andExpect(status().isOk());

        // 如果需要检查响应内容，可以这样进行：
         String content = mockMvc.perform(get("/oom/add").param("size", "2"))
                 .andExpect(status().isOk())
                 .andReturn()
                 .getResponse()
                 .getContentAsString();
         assertEquals("okl ", content);

        // 由于add方法返回的是一个字符串，我们可以直接调用oomController的add方法来验证返回值
//        String result = oomController.add(2);
//        assertEquals("okl ", result);
    }
}
