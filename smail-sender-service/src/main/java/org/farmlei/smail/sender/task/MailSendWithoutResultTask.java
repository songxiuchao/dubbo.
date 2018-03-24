package org.farmlei.smail.sender.task;

import org.farmlei.smail.bean.Email;

/**
 * 没有返回结果的邮件发送
 */
public class MailSendWithoutResultTask extends AbstractTask implements Runnable {
    private Email email;
    public MailSendWithoutResultTask (Email email) {
        this.email = email;
    }
    @Override
    public void run() {
        try {
            Email email = this.email;
            if (email != null) {
                System.out.println("线程[" + Thread.currentThread().getName() + "]正在发送邮件:" + email.toString());
                Thread.sleep(2000);//休眠两秒，模拟邮件发送的过程所消耗的时间
                System.out.println("线程[" + Thread.currentThread().getName() + "]发送完毕:" + email.toString());
//                toSend(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
