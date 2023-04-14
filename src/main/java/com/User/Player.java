package com.User;

import com.view.LoginView;

import javax.swing.*;
import java.net.URL;

public class Player{
    public String playerName;
    public int playerId;

    public ImageIcon color;

    public ImageIcon getColor() {
        return color;
    }

    public int fromPort;
    public int toPort;
    public String toIp;

    public int getFromPort() {
        return fromPort;
    }

    public void setFromPort(int fromPort) {
        this.fromPort = fromPort;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public void setColor(String imgName) {
        URL imgUrl = LoginView.class.getClassLoader().getResource(imgName);
        this.color = new ImageIcon(imgUrl);
    }

    public Player() {
    }

    public Player(String playerName, int playerId) {
        this.playerName = playerName;
        this.playerId = playerId;
    }
    public Player(String playerName, int playerId,int fromPort,int toPort,String toIp) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.fromPort = fromPort;
        this.toPort = toPort;
        this.toIp = toIp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
