package org.farmlei.smail.bean;

import java.io.Serializable;

/**
 * EMAIL对象
 */
public class Email implements Serializable {
    private String from;//发件人
    private String receiver;//收件人
    private String subject;//邮件主题
    private String content;//邮件内容

    public Email(String from, String receiver, String subject, String content) {
        this.from = from;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "【发件人】" + this.getFrom() + "【收件人】" + this.getReceiver() + "【主题】" + this.getSubject() +
                "【内容】" +this.getContent();
    }
}
