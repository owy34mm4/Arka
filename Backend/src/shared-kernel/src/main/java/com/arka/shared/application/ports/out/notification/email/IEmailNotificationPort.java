package com.arka.shared.application.ports.out.notification.email;

public interface IEmailNotificationPort {
    void send (String to , String subject,  String body);
    void sendHtml(String to, String subject, String htmlBody);
}
