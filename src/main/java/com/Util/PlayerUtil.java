package com.Util;

import com.User.Player;
import com.view.LoginView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.net.URL;

public class PlayerUtil {
    public static Logger logger = Logger.getLogger(PlayerUtil.class);
    public static int nowColor = 1;

    public static String result;

    public static boolean firstStep;
    public static boolean isFinish = false;

    public static boolean closeThread = false;

    public static int playerChoose=-1;
    public static ImageIcon chooseImage (Player player1, Player player2){
        if(nowColor==-1){
            nowColor=1;
            return player1.color;
        }else{
            nowColor=-1;
            return player2.color;
        }
    }
    public static int[][] board= new int[20][20];
    public static boolean isCovered(int row,int col){
        if(board[row][col]!=0){
            return true;
        }
        board[row][col]=nowColor;
        if (isRowFinished(row, col)) isFinish = true;
        if (isColFinished(row, col)) isFinish = true;
        if (isLeftOnFinished(row, col)) isFinish = true;
        if(isRightOnFinished(row,col)) isFinish = true;
        if(isFinish){
            if(nowColor==-1){
                result = "Victory!";
            }else{
                result = "Defeat!";
            }
        }
        return false;

    }

    private static boolean isRowFinished(int row, int col) {
        int tempNum  = 0;
        for(int i = row; i>=MathUtil.smax(0, row -5); i--){
            if(board[i][col]==nowColor) tempNum++;
            else break;
        }
        for(int i = row +1; i<=MathUtil.smin(19, row +5); i++){
            if(board[i][col]==nowColor) tempNum++;
            else break;
        }
        if(tempNum>=5) return  true;
        return false;
    }
    private static boolean isColFinished(int row, int col) {
        int tempNum  = 0;
        for(int i = col; i>=MathUtil.smax(0, col -5); i--){
            if(board[row][i]==nowColor) tempNum++;
            else break;
        }
        for(int i = col +1; i<=MathUtil.smin(19, col +5); i++){
            if(board[row][i]==nowColor) tempNum++;
            else break;
        }
        if(tempNum>=5) return true;
        return false;
    }
    private static boolean isLeftOnFinished(int row, int col) {
        int tempNum  = 0;
        for(int i = col,j=row; i>=MathUtil.smax(0, col -5)&&j>=MathUtil.smax(0, row -5); i--,j--){
            if(board[j][i]==nowColor) tempNum++;
            else break;
        }
        for(int i = col +1,j=row+1; i<=MathUtil.smin(19, col +5) && j<=MathUtil.smin(19, row +5); i++,j++){
            if(board[j][i]==nowColor) tempNum++;
            else break;
        }
        if(tempNum>=5) return true;
        return false;
    }
    private static boolean isRightOnFinished(int row, int col) {
        int tempNum  = 0;
        for(int i = col,j=row; i>=MathUtil.smax(0, col -5)&&j<=MathUtil.smin(19, row +5); i--,j++){
            if(board[j][i]==nowColor) tempNum++;
            else break;
        }
        for(int i = col +1,j=row-1; i<=MathUtil.smin(19, col +5) && j>=MathUtil.smax(0, row -5); i++,j--){
            if(board[j][i]==nowColor) tempNum++;
            else break;
        }
        if(tempNum>=5) return true;
        return false;
    }

    public static boolean isWin(){
        if(isFinish){
            logger.info("finished");
        }
        return isFinish;
    }

    public static void clean(){
        nowColor = 1;
        isFinish = false;
        playerChoose = -1;
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                board[i][j]=0;
            }
        }
        closeThread = true;
    }
}
