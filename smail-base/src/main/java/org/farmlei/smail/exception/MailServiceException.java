package org.farmlei.smail.exception;

/**
 * 邮件发送异常
 * @author Administrator
 */
public class MailServiceException extends Exception {

    private static final long serialVersionUID = -598249455534151432L;

    public MailServiceException(String message) {
        super(message);
    }
}
