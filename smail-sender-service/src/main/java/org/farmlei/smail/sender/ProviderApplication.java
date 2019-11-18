package org.farmlei.smail.sender;

import com.reger.dubbo.rpc.filter.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.MailSendException;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages="org.farmlei.smail.sender.provider")
@Slf4j
public class ProviderApplication implements CommandLineRunner, DisposableBean {

    private final static CountDownLatch latch = new CountDownLatch(1);

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) throws InterruptedException {
        // 注册允许传递的异常类型
        Utils.register(MailSendException.class);
        // 注册允许传递的异常类型
        Utils.register(RuntimeException.class);
        context = SpringApplication.run(ProviderApplication.class, args);
        //CountDownLatch.await() 方法在倒计数为0之前会阻塞当前线程
        latch.await();
    }

    @Override
    public void destroy() throws Exception {
        latch.countDown();
        context.close();
        log.info("服务提供者关闭------>>服务关闭");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("服务提供者启动完毕------>>启动完毕");
    }

}
