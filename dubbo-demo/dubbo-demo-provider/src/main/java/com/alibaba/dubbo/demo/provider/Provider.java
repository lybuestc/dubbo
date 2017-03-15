package com.alibaba.dubbo.demo.provider;

/**
 * Created by lybuestc on 17/3/15.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-demo-provider.xml");
        context.start();

        System.in.read(); // 按任意键退出
    }

}