package org.farmlei.smail.exception;

/**
 * 邮件发送异常
 */
public class MailServiceException extends Exception {
    public MailServiceException(String message) {
        super(message);
    }
}
