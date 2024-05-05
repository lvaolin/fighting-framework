/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dhy.oom.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class OomController {

    List<String[][]> list = new ArrayList();
    /**
     * 向系统中添加一个新的二维字符串数组。
     *
     * @param size 数组的大小，是一个正整数，默认值为1。该参数决定了创建的二维字符串数组的行数和列数。
     * @return 返回一个简单的字符串提示，表明操作完成。
     */
    @RequestMapping("/oom/add")
    @ResponseBody
    public String add(@RequestParam(name = "size", defaultValue = "1") int size) {
        // 在列表中添加一个新的二维字符串数组，其大小为参数size指定的值。
        list.add(new String[size][size]);
        return "okl ";
    }

    /**
     * 请求处理路径为"/oom/remove"的方法。
     * 该方法不接受任何参数，清除列表对象list中的所有元素，并返回一个字符串"okl"。
     *
     * @return 返回一个表示操作结果的字符串"okl"。
     */
    @RequestMapping("/oom/remove")
    @ResponseBody
    public String remove() {
        // 清除list列表中的所有元素
        list.clear();
        return "okl";
    }


}
