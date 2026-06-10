package com.example.schooldairy.service;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    public void sendMessage(
            String mobile,
            String message
    ) {

        System.out.println(
                "WhatsApp sent to "
                        + mobile
                        + " : "
                        + message
        );
    }
}
