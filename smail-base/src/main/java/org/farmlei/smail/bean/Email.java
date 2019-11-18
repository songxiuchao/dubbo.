package org.farmlei.smail.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * EMAIL对象
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = -1003042221163516700L;

    private String from;//发件人
    private String receiver;//收件人
    private String subject;//邮件主题
    private String content;//邮件内容

    @Override
    public String toString() {
        return "【发件人】" + this.getFrom() + "【收件人】" + this.getReceiver() + "【主题】" + this.getSubject() +
                "【内容】" +this.getContent();
    }
}
