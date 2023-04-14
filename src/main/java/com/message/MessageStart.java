package com.message;

import com.view.MainView;

public class MessageStart {
    public static void startMessage(MainView mainView,int port,String msgFrom) {
//        new Thread(new MessageSend(7237,"localhost",9999)).start();
        new Thread(new MessageReceive(mainView.getPlayer().fromPort,"老师",mainView)).start();
    }

    public static Thread sendMessage(MainView mainView){
        return new Thread(new MessageSend((int) ((Math.random()*10000)%1000),mainView.getPlayer().toIp,mainView.getPlayer().toPort));
    }
}
