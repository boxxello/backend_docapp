package com.docapp.springjwt.payload.request;

import java.time.LocalDateTime;
import java.util.Date;

public class MessaggioRequest {

    private String conversationId;
    private String message;

    private String sender;


    private String receiver;

    public MessaggioRequest() {
    }

    public MessaggioRequest(String conversationId, String message, String sender, String receiver) {
        this.conversationId = conversationId;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessaggioRequest{" +
                "conversationId=" + conversationId +
                ", message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
