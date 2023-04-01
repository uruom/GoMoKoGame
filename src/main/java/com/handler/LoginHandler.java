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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text)){
//            System.out.println("重置");
            loginView.getPwdFiled().setText("");
            loginView.getUserTxt().setText("");
        }else{
            if("登录".equals(text)){
                login();
            }
        }

    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        String pwd = loginView.getPwdFiled().getText();
        System.out.println(user+" "+pwd);
        if(user==null||"".equals(user.trim())||
                pwd==""||"".equals(pwd.trim())){
            JOptionPane.showMessageDialog(loginView,"用户密码必填");

        }
        AdminService adminService = new AdminServiceImpl();
        AdminDo adminDo = new AdminDo();
        adminDo.setUserName(user);
        adminDo.setPwd(pwd);

        boolean flag = adminService.validateAdmin(adminDo);
        if (flag) {
//            跳转到主界面并销毁登录界面
            new MenuView(new Player(user,2),new Player("oppenent",1));
            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView,"用户不存在");
        }
//                System.out.println("登录");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER) {
            login();
        }
    }
}
