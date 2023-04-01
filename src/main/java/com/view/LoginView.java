package com.view;

import com.handler.LoginHandler;
//import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


public class LoginView extends JFrame {
    public static Logger logger = Logger.getLogger(LoginView.class);
    JLabel nameLable = new JLabel("五子棋",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLable = new JLabel("用户名");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码");
    JTextField pwdFiled = new JTextField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");

    SystemTray systemTray;
    TrayIcon trayIcon;
    LoginHandler loginHandler;
    public LoginView(){
        super("Login View");
        loginHandler = new LoginHandler(this);
        URL imgUrl = LoginView.class.getClassLoader().getResource("keqing.png");
        System.out.println(imgUrl);
        if(imgUrl==null)
            throw new RuntimeException("位置");
//        ImageIcon
        setIconImage(new ImageIcon(imgUrl).getImage());
        Container contentPane = getContentPane();

        nameLable.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLable.setPreferredSize(new Dimension(0,80));
        Font centerFont = new Font("楷体",Font.PLAIN,20);
        userNameLable.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdFiled.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
//        加入面板
        centerPanel.add(userNameLable);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdFiled);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);

        layoutCenter();

        contentPane.add(nameLable,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        if(SystemTray.isSupported()){
            systemTray = SystemTray.getSystemTray();
            URL iconUrl = LoginView.class.getClassLoader().getResource("img34.jpg");

            trayIcon = new TrayIcon(new ImageIcon(iconUrl).getImage());
            trayIcon.setImageAutoSize(true);
//            图片自动缩放
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
//            增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
//            托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCOunt = e.getClickCount();
                    if(clickCOunt==1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
//                    super.mouseClicked(e);
                }
            });
        }
//        设置loginbutton为默认按钮
        getRootPane().setDefaultButton(loginBtn);

        setSize(600,400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void layoutCenter() {
        //       布局
        Spring userNameLabelWidth = Spring.width(userNameLable);
        Spring userTxtWidth = Spring.width(userTxt);
        Spring spaceWidth  = Spring.constant(20);
        Spring childWidth = Spring.sum(Spring.sum(userNameLabelWidth,userTxtWidth),spaceWidth);
        int offsetX = childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLable,-offsetX,SpringLayout.HORIZONTAL_CENTER, centerPanel);
//        Spring childHeight = Spring.sum(Spring.height(userTxt),Spring.constant(100));
//        int offsetY = childHeight.getValue() / 2;
//        springLayout.putConstraint(SpringLayout.SOUTH,userNameLable,-offsetY,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLable,20,SpringLayout.NORTH, centerPanel);
//        userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLable);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLable);
//        pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLable);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,40,SpringLayout.NORTH,userNameLable);
//        pwdTxt
        springLayout.putConstraint(SpringLayout.WEST,pwdFiled,0,SpringLayout.WEST,userTxt);
        springLayout.putConstraint(SpringLayout.NORTH,pwdFiled,0,SpringLayout.NORTH,pwdLabel);
//      loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,30,SpringLayout.SOUTH,pwdLabel);
//      resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,30,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.SOUTH,resetBtn,0,SpringLayout.SOUTH,loginBtn);
    }

    public static void main(String[] args){
//        logger.info("進入");
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public void setUserTxt(JTextField userTxt) {
        this.userTxt = userTxt;
    }

    public JTextField getPwdFiled() {
        return pwdFiled;
    }

    public void setPwdFiled(JTextField pwdTxt) {
        this.pwdFiled = pwdTxt;
    }
}
