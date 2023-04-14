package com.view;

import com.User.Player;
import com.Util.PlayerUtil;
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
import java.awt.peer.MenuPeer;
import java.net.URL;

public class MenuView extends JFrame{
    public static Logger logger = Logger.getLogger(LoginView.class);

    SpringLayout springLayout = new SpringLayout();

    JPanel centerPanel = new JPanel(springLayout);
    JButton gameStartBtn = new JButton("新建游戏");
    JButton gameExitBtn = new JButton("退出游戏");

    JLabel toIpLabel = new JLabel("对方IP");
    JTextField toIpFiled = new JTextField();

    JLabel toPortLabel = new JLabel("对方端口");
    JTextField toPortFiled = new JTextField();

    JLabel fromPortLabel = new JLabel("己方端口");

    JTextField fromPortFiled = new JTextField();


    public JTextField getToPortFiled() {
        return toPortFiled;
    }

    public JTextField getFromPortFiled() {
        return fromPortFiled;
    }



    MenuHandler menuHandler;

    public JTextField getToIpFiled() {
        return toIpFiled;
    }

    public MenuView(Player player, Player oppenent){
        super("Menu View");
        menuHandler = new MenuHandler(this,player,oppenent);
        URL imgUrl = LoginView.class.getClassLoader().getResource("keqing.png");
        System.out.println(imgUrl);
        if(imgUrl==null)
            throw new RuntimeException("位置");
//        ImageIcon
        setIconImage(new ImageIcon(imgUrl).getImage());
        Container contentPane = getContentPane();
        PlayerUtil.closeThread = true;

        Font centerFont = new Font("楷体",Font.PLAIN,20);
        toIpLabel.setFont(centerFont);
        toIpFiled.setPreferredSize(new Dimension(200,30));

        fromPortLabel.setFont(centerFont);
        fromPortFiled.setPreferredSize(new Dimension(200,30));

        toPortLabel.setFont(centerFont);
        toPortFiled.setPreferredSize(new Dimension(200,30));

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

        springLayout.putConstraint(SpringLayout.WEST,toIpLabel,0,SpringLayout.WEST, gameExitBtn);
        springLayout.putConstraint(SpringLayout.SOUTH,toIpLabel,50,SpringLayout.SOUTH, gameStartBtn);

        springLayout.putConstraint(SpringLayout.WEST,toIpFiled,20,SpringLayout.EAST, toIpLabel);
        springLayout.putConstraint(SpringLayout.SOUTH,toIpFiled,0,SpringLayout.SOUTH, toIpLabel);

        springLayout.putConstraint(SpringLayout.WEST,fromPortLabel,0,SpringLayout.WEST, toIpLabel);
        springLayout.putConstraint(SpringLayout.SOUTH,fromPortLabel,50,SpringLayout.SOUTH, toIpLabel);

        springLayout.putConstraint(SpringLayout.WEST,fromPortFiled,0,SpringLayout.WEST, toIpFiled);
        springLayout.putConstraint(SpringLayout.SOUTH,fromPortFiled,0,SpringLayout.SOUTH, fromPortLabel);

        springLayout.putConstraint(SpringLayout.WEST,toPortLabel,0,SpringLayout.WEST, fromPortLabel);
        springLayout.putConstraint(SpringLayout.SOUTH,toPortLabel,50,SpringLayout.SOUTH, fromPortLabel);

        springLayout.putConstraint(SpringLayout.WEST,toPortFiled,0,SpringLayout.WEST, fromPortFiled);
        springLayout.putConstraint(SpringLayout.SOUTH,toPortFiled,0,SpringLayout.SOUTH, toPortLabel);

        centerPanel.add(toIpFiled);
        centerPanel.add(toIpLabel);

        centerPanel.add(fromPortFiled);
        centerPanel.add(fromPortLabel);

        centerPanel.add(toPortFiled);
        centerPanel.add(toPortLabel);

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
