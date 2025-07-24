package com.smart.smartcontact.helper;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String msg1;
    private MessageType type = MessageType.blue;

    public String getMsg1() {
        return msg1;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }
}
