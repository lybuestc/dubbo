package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.demo.DemoService;

/**
 * Created by lybuestc on 17/3/16.
 */
public class ConsumerMain {
    public static void main(String[] args){
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig("hello-world-app");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("zookeeper://127.0.0.1:2181");

// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

// 引用远程服务
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");

// 和本地bean一样使用xxxService
        DemoService demoService = reference.get();// 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        for(int i=0;i<10;i++){
            String result = demoService.sayHello(" haha ....");
            System.out.println(result);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
