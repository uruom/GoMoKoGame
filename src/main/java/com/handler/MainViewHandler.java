package com.handler;

import com.User.Player;
import com.Util.MathUtil;
import com.Util.PlayerUtil;
import com.message.Message;
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
    public static Logger logger = Logger.getLogger(MainViewHandler.class);
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
        if(PlayerUtil.firstStep){

        }
        if(PlayerUtil.playerChoose!=PlayerUtil.nowColor) return;
        logger.info("choose:"+PlayerUtil.playerChoose+" color:"+PlayerUtil.nowColor);
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
        if(PlayerUtil.nowColor==-1){
            mainView.getOpponentTurnLable().setVisible(true);
            mainView.getPlayerTurnLable().setVisible(false);
        }else{
            mainView.getPlayerTurnLable().setVisible(true);
            mainView.getOpponentTurnLable().setVisible(false);
        }
        if(PlayerUtil.nowColor==-1){
            Message.setData(row,col);
//            不能多次启动start，需要用run
            mainView.thread.run();
        }
        jButton.setIcon(PlayerUtil.chooseImage(player,opponent));

        if(PlayerUtil.isWin()){
            PlayerUtil.clean();

            JOptionPane.showMessageDialog(mainView,PlayerUtil.result);
            new MenuView(player,opponent);
            mainView.dispose();
//            这个run是为了终结发送程序，socket close
            mainView.thread.run();
        }


//        synchronized ((Object) PlayerUtil.playerChoose){
//            if(PlayerUtil.playerChoose==-1){
//                PlayerUtil.playerChoose=1;
//                notifyAll();
//            }
//        }


        logger.info("row="+row+"col="+col);
    }
}
