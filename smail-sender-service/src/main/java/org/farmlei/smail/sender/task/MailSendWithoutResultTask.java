package org.farmlei.smail.sender.task;

import org.farmlei.smail.bean.Email;
import org.farmlei.smail.sender.util.MailUtil;

/**
 * 没有返回结果的邮件发送
 * @author Administrator
 */
public class MailSendWithoutResultTask extends AbstractTask implements Runnable {
    private Email email;
    public MailSendWithoutResultTask (Email email) {
        this.email = email;
    }

    @Override
    void toSend(Email email) {
        MailUtil.doSendEmail(email);
    }

    /**
     * 发送邮件
     */
    @Override
    public void run() {
        try {
            Email email = this.email;
            if (email != null) {
                email.setFrom("2323214889@qq.com");
                email.setReceiver("1528856973@qq.com");
                email.setContent("模拟发送邮件");
                email.setSubject("模拟发送邮件主题");
                toSend(email);
                System.out.println("线程[" + Thread.currentThread().getName() + "]正在发送邮件:" + email.toString());
                //休眠两秒，模拟邮件发送的过程所消耗的时间
                Thread.sleep(2000);
                System.out.println("线程[" + Thread.currentThread().getName() + "]发送完毕:" + email.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
