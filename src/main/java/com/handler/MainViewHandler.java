package com.handler;

import com.User.Player;
import com.Util.PlayerUtil;
import com.service.impl.AdminServiceImpl;
import com.view.LoginView;
import com.view.MainView;
import com.view.MenuView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Optional;

public class MainViewHandler implements ActionListener {
    public static Logger logger = Logger.getLogger(AdminServiceImpl.class);
    private MainView mainView;

    private Player player;
    private Player opponent;
    public MainViewHandler(MainView mainView, Player player, Player opponent){
        this.mainView = mainView;
        this.player = player;
        this.opponent = opponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
//        URL imgUrlBlack = LoginView.class.getClassLoader().getResource("blackStone.gif");
//        URL imgUrlWhite = LoginView.class.getClassLoader().getResource("whiteStone.gif");
//
//        jButton.setIcon(new ImageIcon(imgUrlBlack));
        String name = jButton.getName();
        int row = 0;
        int colStart=0;
        int col=0;
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)!=','){
                row*=10;
                row+=name.charAt(i)-'0';
            }else{
                colStart = i;
                break;
            }
        }
        for(int i=colStart+1;i<name.length();i++){
            col*=10;
            col+=name.charAt(i)-'0';
        }

        if(PlayerUtil.isCovered(row,col)){
            return ;
        }
        jButton.setIcon(PlayerUtil.chooseImage(player,opponent));
        if(PlayerUtil.isWin()){
            PlayerUtil.clean();
            JOptionPane.showMessageDialog(mainView,"Victory！");
            new MenuView(player,opponent);
            mainView.dispose();
        }

        logger.info("row="+row+"col="+col);
    }
}
