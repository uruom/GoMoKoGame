package com.message;

public class MessageStart {
    public static void main(String[] args) {
        new Thread(new MessageSend(7237,"localhost",9999)).start();
        new Thread(new MessageReceive(8888,"老师")).start();
    }
}
