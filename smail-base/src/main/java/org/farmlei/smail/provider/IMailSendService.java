package org.farmlei.smail.provider;

import org.farmlei.smail.exception.MailServiceException;

/**
 * dubbo-RPC邮件发送接口
 * @author Administrator
 */
public interface IMailSendService {
    /**
     * 发送邮件
     * @param from 发件人姓名/邮箱
     * @param receiver 收件人
     * @param subject 主题
     * @param content 内容
     * @throws MailServiceException
     */
    void send(String from, String receiver, String subject, String content) throws MailServiceException;
}
