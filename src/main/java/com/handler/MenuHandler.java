package com.handler;

import com.User.Player;
import com.entity.AdminDo;
import com.service.AdminService;
import com.service.impl.AdminServiceImpl;
import com.view.LoginView;
import com.view.MainView;
import com.view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuHandler implements ActionListener {
    private MenuView menuView;
    private Player player;
    private Player opponent;
    public MenuHandler(MenuView menuView,Player player, Player opponent){
        this.menuView = menuView;
        this.opponent = opponent;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("新建游戏")){
            login();
        }else{
            System.exit(0);
        }


    }

    private void login() {
        String user = "menuView.getUserTxt().getText()";
        String pwd = "menuView.getPwdFiled().getText()";
        System.out.println(user+" "+pwd);

        AdminService adminService = new AdminServiceImpl();
        AdminDo adminDo = new AdminDo();
        adminDo.setUserName(user);
        adminDo.setPwd(pwd);

        boolean flag = true;
        if (flag) {
//            跳转到主界面并销毁登录界面
            new MainView(new Player(user,2),new Player("opponent",1));
            menuView.dispose();
        }else{
            JOptionPane.showMessageDialog(menuView,"用户不存在");
        }
//                System.out.println("登录");
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER) {
            login();
        }
    }
}
