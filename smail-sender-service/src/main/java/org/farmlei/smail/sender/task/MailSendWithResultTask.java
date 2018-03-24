package org.farmlei.smail.sender.task;

import org.farmlei.smail.bean.Email;
import java.util.concurrent.Callable;

/**
 * 有返回结果的邮件发送
 */
public class MailSendWithResultTask extends AbstractTask implements Callable<String> {
    private Email email;
    public MailSendWithResultTask (Email email) {
        this.email = email;
    }

    @Override
    public String call() throws Exception {
        try {
            Email email = this.email;
            if (email != null) {
//                Thread.sleep(2000);//休眠两秒，模拟邮件发送的过程所消耗的时间
                toSend(email);
                return "邮件：" + email.toString() + "发送成功";
            } else {
                return "发送失败：邮件参数不正确";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败：" + e.getMessage();
        }
    }
}
