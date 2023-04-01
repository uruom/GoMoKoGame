package com.message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MessageReceive implements Runnable{
    DatagramSocket socket = null;
    private int port;
    private String msgFrom;

    public MessageReceive(int port,String  msgFrom) {
        this.port = port;
        this.msgFrom = msgFrom;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }

    public void run() {

        //        准备接收包裹


        while(true){

            try {
                byte[] container = new byte[1024];

                DatagramPacket packet = new DatagramPacket(container,0,container.length);
//            断开链接
                socket.receive(packet);//阻塞式接收包裹
                byte[] data = packet.getData();
                String receiveData = new String(data, 0, data.length);

                System.out.println(msgFrom+":"+receiveData.trim());
                if (receiveData.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }
        socket.close();
    }
}
