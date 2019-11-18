package org.farmlei.smail.sender.handler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.farmlei.smail.bean.Email;
import org.farmlei.smail.sender.task.MailSendWithoutResultTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 邮件发送处理器，多线程调度
 * 单例模式
 * @author Administrator
 */
@Slf4j
public class MailSendHandler {
    //日志记录器

    private MailSendHandler() {
        takeDataToProcess();
    }

    /**
     * 内部静态工厂类，用于在初始化的时候生产一个处理器
     */
    private static class MailSendHandlerFactory {
        private static MailSendHandler mailSender = new MailSendHandler();
    }

    /**
     * 获取单例对象
     * @return
     */
    public static MailSendHandler getInstance() {
        return MailSendHandlerFactory.mailSender;
    }

    /**
     * 手动创建线程池
     */
    private ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,10,0L,TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024),new ThreadFactoryBuilder().setNameFormat("xxx-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
    //private ExecutorService executorService = Executors.newFixedThreadPool(5);
//    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 待发送的邮件队列
     */
    private static BlockingQueue<Email> queue = new LinkedBlockingQueue<>();

    /**
     * 把待发送的邮件放进队列
     * @param email 待发送的邮件
     */
    public void pushDataToQueue(Email email) {
        queue.offer(email);
    }

    /**
     * 不停的循环从队列中拿邮件进行发送
     */
    public void takeDataToProcess() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Email email = queue.take();
                    log.info("从队列拿到邮件：{}", email.toString());
                    //第一种：不返回发送结果
                    threadPoolExecutor.execute(new MailSendWithoutResultTask(email));

                    //第二种：返回发送结果，并打印
                    //Future<String> future = executorService.submit(new MailSendWithResultTask(email));
                    //System.out.println(future.get());

                   //每隔200毫秒从队列取一次
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
