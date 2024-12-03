package com.avila.notifications.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service public class MessagingService {

    private final AmazonSNS sns;

    public void send(String contact, String message) {
        PublishRequest request = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(contact);

        sns.publish(request);
    }

}
