package com.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解方式使用sentinel
 * @author lvaolin
 * @create 2020/3/19 2:48 下午
 */
public class Demo02 {

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        while (true) {
            helloWorld("lval");
        }
    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


    @SentinelResource(value = "HelloWorld",fallback = "helloWorldFallback",blockHandler = "exceptionHandler")
    public static void  helloWorld(String name) {
        // 资源中的逻辑
        System.out.println(name + " hello world");
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public void helloWorldFallback(String name,Throwable throwable) {
        System.out.println("备用逻辑---");
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public void exceptionHandler(String name, BlockException ex) {
        // Do some log here.
        System.out.println("Block 异常处理");

    }
}
