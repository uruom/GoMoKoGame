package com.message;

import com.Util.PlayerUtil;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class MessageSend implements Runnable{
    public static Logger logger = Logger.getLogger(MessageSend.class);

    DatagramSocket socket = null;
    BufferedReader reader = null;



    String data = null;

    private int fromPort;
    private String toIp;
    private int toPort;

    public MessageSend(int fromPort, String toIp, int toPort) {
        this.fromPort = fromPort;
        this.toIp = toIp;
        this.toPort = toPort;
        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }

    public void run() {

//        准备数据：从控制台读取System.in

        if(PlayerUtil.closeThread){
            logger.info("sendClose!");
            socket.close();
            return ;
        }
        try {
                byte[] datas = Message.data.getBytes();
                DatagramPacket packet = new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIp,this.toPort));
                socket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        System.out.println("Success send");
    }

}
