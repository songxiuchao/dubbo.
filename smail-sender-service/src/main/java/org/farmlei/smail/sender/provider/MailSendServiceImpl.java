package org.farmlei.smail.sender.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.farmlei.smail.bean.Email;
import org.farmlei.smail.exception.MailServiceException;
import org.farmlei.smail.provider.IMailSendService;
import org.farmlei.smail.sender.handler.MailSendHandler;
import org.springframework.beans.factory.annotation.Value;

/**
 * dubbo RPC接口实现类
 */
@Service(protocol="dubbo-hessian")
public class MailSendServiceImpl implements IMailSendService {

    @Override
    public void send(String from, String receiver, String subject, String content) throws MailServiceException {
        if (!StringUtils.isBlank(receiver)
                && !StringUtils.isBlank(subject)
                && !StringUtils.isBlank(content)) {
            if (StringUtils.isBlank(from)) {
                from = "aaa";
            }
            Email email = new Email(from, receiver, subject, content);
            MailSendHandler mailSendHandler = MailSendHandler.getInstance();
            mailSendHandler.pushDataToQueue(email);
        } else {
            throw new MailServiceException("发件人、收件人、主题和内容不能为空");
        }
    }
}
