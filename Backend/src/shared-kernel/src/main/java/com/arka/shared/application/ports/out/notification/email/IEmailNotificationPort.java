package com.arka.shared.application.ports.out.notification.email;

import java.util.Map;

public interface IEmailNotificationPort {
    void send (String to , String subject,  String body);
    void sendHtml(String to, String subject, String templateName, Map<String, Object> variables);
}
