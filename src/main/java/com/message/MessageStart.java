package com.message;

import com.view.MainView;

public class MessageStart {
    public static void startMessage(MainView mainView) {
//        new Thread(new MessageSend(7237,"localhost",9999)).start();
        new Thread(new MessageReceive(8888,"老师",mainView)).start();
    }

    public static Thread sendMessage(){
        return new Thread(new MessageSend(7237,"localhost",9999));
    }
}
