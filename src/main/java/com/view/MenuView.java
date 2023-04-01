package com.view;

import com.User.Player;
import com.handler.LoginHandler;
import com.handler.MainViewHandler;
import com.handler.MenuHandler;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class MenuView extends JFrame{
    public static Logger logger = Logger.getLogger(LoginView.class);

    SpringLayout springLayout = new SpringLayout();

    JPanel centerPanel = new JPanel(springLayout);
    JButton gameStartBtn = new JButton("新建游戏");
    JButton gameExitBtn = new JButton("退出游戏");


    MenuHandler menuHandler;
    public MenuView(Player player,Player oppenent){
        super("Menu View");
        menuHandler = new MenuHandler(this,player,oppenent);
        URL imgUrl = LoginView.class.getClassLoader().getResource("keqing.png");
        System.out.println(imgUrl);
        if(imgUrl==null)
            throw new RuntimeException("位置");
//        ImageIcon
        setIconImage(new ImageIcon(imgUrl).getImage());
        Container contentPane = getContentPane();

        gameStartBtn.setFont(new Font("华文行楷",Font.PLAIN,40));
        gameStartBtn.setPreferredSize(new Dimension(300,50));
        gameStartBtn.addActionListener(menuHandler);

        gameExitBtn.setFont(new Font("华文行楷",Font.PLAIN,40));
        gameExitBtn.setPreferredSize(new Dimension(300,50));
        gameExitBtn.addActionListener(menuHandler);



        springLayout.putConstraint(SpringLayout.WEST,gameExitBtn,150,SpringLayout.WEST, centerPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,gameExitBtn,-200,SpringLayout.SOUTH, centerPanel);

        springLayout.putConstraint(SpringLayout.WEST,gameStartBtn,0,SpringLayout.WEST, gameExitBtn);
        springLayout.putConstraint(SpringLayout.SOUTH,gameStartBtn,-200,SpringLayout.NORTH, gameExitBtn);




        centerPanel.add(gameStartBtn);
        centerPanel.add(gameExitBtn);
        contentPane.add(centerPanel);
//        contentPane.add(gameStartBtn,BorderLayout.NORTH);


//        设置loginbutton为默认按钮
        getRootPane().setDefaultButton(gameStartBtn);

        setSize(600,800);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){
//        logger.info("進入");
        new MenuView(new Player("ser",2),new Player("oppenent",1));
    }

}
