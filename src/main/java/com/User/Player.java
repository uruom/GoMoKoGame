package com.User;

import com.view.LoginView;

import javax.swing.*;
import java.net.URL;

public class Player {
    public String playerName;
    public int playerId;

    public ImageIcon color;

    public ImageIcon getColor() {
        return color;
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
