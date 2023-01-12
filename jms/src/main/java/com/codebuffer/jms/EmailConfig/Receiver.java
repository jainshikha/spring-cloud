package com.codebuffer.jms.EmailConfig;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox",containerFactory = "myContainerFactory")
    public void receiveMessage(Email email){
        System.out.println("Received the email < "+ email + " >");
    }
}
