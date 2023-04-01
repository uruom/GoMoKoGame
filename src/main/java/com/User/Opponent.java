package com.User;

import com.Util.OpponentUtil;
import com.Util.PlayerUtil;
import com.view.LoginView;
import com.view.MainView;
import org.apache.log4j.Logger;

import javax.swing.*;

import static com.Util.PlayerUtil.playerChoose;
import static java.lang.Thread.sleep;

public class Opponent {
    public static Logger logger = Logger.getLogger(Opponent.class);
    MainView mainView;
    public Opponent(MainView mainView){
        logger.info("init Success!");
        this.mainView = mainView;
    }
    public void playChess(){
        logger.info("start Thread Opponent");
        while(true){
            if(playerChoose==-1){
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                JButton[][] jBoard = mainView.getjBoard();
                int row = (int) (Math.random()*312%20);
                int col = (int) (Math.random()*312%20);
                if(PlayerUtil.board[row][col]==0){
                    jBoard[row][col].doClick();
                    playerChoose = -1;
                }

            }
            if(PlayerUtil.closeThread){
                logger.info("Thread end!");
                break;
            }

        }
//        while(true){
//            synchronized ((Object) playerChoose){
//                if(playerChoose==-1){
//                    try {
//                        logger.info("begin try");
//                        this.wait();
//                    } catch (InterruptedException e) {
//                        logger.error("error wait Fail!!!");
//                        throw new RuntimeException(e);
//                    }
//                }else{
//                    JButton[][] jBoard = mainView.getjBoard();
//                    int row = (int) (Math.random()*312%20);
//                    int col = (int) (Math.random()*312%20);
//                    jBoard[row][col].doClick();
//                    playerChoose = -1;
//                    notifyAll();
//                }
//            }
//        }
    }


}
