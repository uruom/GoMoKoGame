package com.view;

import com.User.Opponent;
import com.User.Player;
import com.Util.DimensionUtil;
import com.Util.OpponentUtil;
import com.Util.PlayerUtil;
import com.handler.MainViewHandler;
import com.message.MessageStart;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class MainView extends JFrame {

    public static Logger logger = Logger.getLogger(MainView.class);


    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);

    JButton searchBtn = new JButton("查询");
    SpringLayout springLayout = new SpringLayout();
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    JPanel centerPanel = new JPanel(springLayout);

    public Thread thread;

    public JButton[][] getjBoard() {
        return jBoard;
    }

    public void setjBoard(JButton[][] jBoard) {
        this.jBoard = jBoard;
    }

    //    JLabel[][] jBoard = new JLabel[20][20];
    JButton[][] jBoard = new JButton[20][20];
    TrayIcon trayIcon;


    private int pageNow = 1;
    private int pageSize = 10;//一页10条
    MainViewHandler mainViewHandler;
    Player player;
    Player opponent;
    public MainView(Player player,Player opponent){
        super("MainView");
        this.player = player;
        this.opponent = opponent;
//        這個地方的set有問題，位置部隊後面環位置！！！！
        player.setColor("blackStone.gif");
        opponent.setColor("whiteStone.gif");
        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);


        mainViewHandler = new MainViewHandler(this,player,opponent);
//        北边组件
//        layoutNorth(contentPane);
//        设置中间的Table
        layoutCenter(contentPane);
//        南边组件
//        layoutSouth(contentPane);


//        根据屏幕大小来写
        setBounds(DimensionUtil.getBounds());
//        设置窗体完全充满屏幕可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void layoutCenter(Container contentPane) {
        int imgWidth = 40,imgHeight = 40,leftDis = 500,onDis = 100,imgGap=2;
        URL imgUrl = LoginView.class.getClassLoader().getResource("background.gif");
        URL imgUrl2 = LoginView.class.getClassLoader().getResource("keqing.png");
        System.out.println(imgUrl);
        if(imgUrl==null)
            throw new RuntimeException("error keqing");
        jBoard[0][0] = new JButton(new ImageIcon(imgUrl));
        jBoard[0][0].setPreferredSize(new Dimension(imgWidth,imgHeight));
        jBoard[0][0].setAutoscrolls(true);
        jBoard[0][0].setName("0,0");
        jBoard[0][0].addActionListener(mainViewHandler);
        centerPanel.add(jBoard[0][0]);
        springLayout.putConstraint(SpringLayout.WEST,jBoard[0][0],leftDis,SpringLayout.WEST, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,jBoard[0][0],onDis,SpringLayout.NORTH, centerPanel);
        for(int i=1;i<20;i++){
            jBoard[0][i] = new JButton(new ImageIcon(imgUrl));
            jBoard[0][i].setPreferredSize(new Dimension(imgWidth,imgHeight));
            jBoard[0][i].setAutoscrolls(true);
            jBoard[0][i].setName("0,"+i);
            jBoard[0][i].addActionListener(mainViewHandler);
            centerPanel.add(jBoard[0][i]);
            springLayout.putConstraint(SpringLayout.WEST,jBoard[0][i],imgHeight+imgGap,SpringLayout.WEST, jBoard[0][i-1]);
            springLayout.putConstraint(SpringLayout.NORTH,jBoard[0][i],0,SpringLayout.NORTH, jBoard[0][i-1]);
        }
        for(int i=1;i<20;i++){
            for(int j=0;j<20;j++){
                jBoard[i][j] = new JButton(new ImageIcon(imgUrl));
                jBoard[i][j].setPreferredSize(new Dimension(imgWidth,imgHeight));
                jBoard[i][j].setAutoscrolls(true);
                jBoard[i][j].setName(i+","+j);
                jBoard[i][j].addActionListener(mainViewHandler);
                centerPanel.add(jBoard[i][j]);
                springLayout.putConstraint(SpringLayout.WEST,jBoard[i][j],0,SpringLayout.WEST, jBoard[i-1][j]);
                springLayout.putConstraint(SpringLayout.NORTH,jBoard[i][j],imgHeight+imgGap,SpringLayout.NORTH, jBoard[i-1][j]);
            }
        }




//        jBoard[1][1] = new JLabel(new ImageIcon(imgUrl2));
//        jBoard[1][1].setPreferredSize(new Dimension(50,50));
//        jBoard[1][1].setAutoscrolls(true);
//        centerPanel.add(jBoard[1][1]);

        contentPane.add(centerPanel,BorderLayout.CENTER);


//        Opponent opponent = new Opponent(this);
        PlayerUtil.closeThread = false;
//        MessageStart.startMessage(this);

        thread = MessageStart.sendMessage();
//        thread = new Thread(()->messageStart.startMessage(this));
//        thread.start();
        new Thread(()-> new MessageStart().startMessage(this)).start();

//        for(int i=0;i<20;i++){
//
//            jBoard[i][0].doClick();
//        }

    }



    //设置是否可见




    public static void main(String[] args){
        new MainView(new Player("uruom",1),new Player("oppenent",2));
    }


    public void dispose(){
        super.dispose();
    }


}
