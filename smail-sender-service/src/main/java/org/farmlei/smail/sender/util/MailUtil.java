package org.farmlei.smail.sender.util;

import org.farmlei.smail.bean.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 邮件工具类
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;//执行者
    private static MailUtil mailUtil;

    //通过这种方式注入JavaMailSender
    @PostConstruct
    public void init() {
        mailUtil = this;
    }
    /**
     * 发送邮件
     * @param email 邮件对象
     */
    public static void doSendEmail(Email email) throws MailException {
        if (email != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFrom());
            message.setTo(email.getReceiver());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            mailUtil.mailSender.send(message);
        }
    }
}
