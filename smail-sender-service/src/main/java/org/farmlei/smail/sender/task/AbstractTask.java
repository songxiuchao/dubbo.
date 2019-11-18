package org.farmlei.smail.sender.task;

import org.farmlei.smail.bean.Email;
import org.farmlei.smail.sender.util.MailUtil;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public abstract class AbstractTask {

    /**
     * 公共发送方法
     * @param email 邮件对象
     */
    void toSend(Email email) {
        MailUtil.doSendEmail(email);
    }
}
