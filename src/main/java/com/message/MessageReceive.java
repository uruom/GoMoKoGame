package com.message;

import com.User.Opponent;
import com.Util.PlayerUtil;
import com.view.MainView;
import org.apache.log4j.Logger;
import sun.security.x509.RDN;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static java.lang.Thread.sleep;

public class MessageReceive implements Runnable{
    public static Logger logger = Logger.getLogger(MessageReceive.class);

    DatagramSocket socket = null;
    private int port;
    private String msgFrom;

    MainView mainView;

    public MessageReceive(int port, String  msgFrom, MainView mainView) {
        this.port = port;
        this.msgFrom = msgFrom;
        this.mainView = mainView;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        logger.info("Thread init Success!");


    }

    public void run() {

        //        准备接收包裹


        while(true){
            if(PlayerUtil.closeThread){
                break;
            }

            try {
                byte[] container = new byte[1024];

                DatagramPacket packet = new DatagramPacket(container,0,container.length);
//            断开链接
                socket.receive(packet);//阻塞式接收包裹
                byte[] data = packet.getData();
                String receiveData = new String(data, 0, data.length);

                int row = 0;
                int colStart=0;
                int col=0;
                receiveData = receiveData.trim();
                if(PlayerUtil.nowColor == -1) {
                    try {
                        sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (receiveData.equals("bye")) {
                    break;
                }
                for(int i=0;i<receiveData.length();i++){
                    if(receiveData.charAt(i)!=','){
                        row*=10;
                        row+=receiveData.charAt(i)-'0';
                    }else{
                        colStart = i;
                        break;
                    }
                }
                for(int i=colStart+1;i<receiveData.length();i++){
                    col*=10;
                    col+= receiveData.charAt(i)-'0';
                }
                PlayerUtil.playerChoose=1;
                mainView.getjBoard()[row][col].doClick();
                PlayerUtil.playerChoose = -1;
                if(PlayerUtil.closeThread){
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        socket.close();
        logger.info("socket close!");
    }
}
