# GoMoKoGame
系统概述
本系统是一个基于 Java 语言实现的五子棋游戏，旨在提供一个多人在线对战的平台。
玩家可以通过本系统开展实时对战，享受游戏的乐趣。 系统架构
本系统采用微服务模式 (Microservices Pattern )架构，对于每一个运行程序，其
本身既是具体架构如下:
游戏登录服务
 采用 Java 语言实现，运行在客户端。
 采用 Swing 实现游戏登录界面，并添加 ActionListener 完成事件监听。 游戏大厅服务
 采用 Java 语言实现，运行在客户端。
 可根据用户心意实时变更对战目标，仅需目标 IP 和约定码即可。 后台服务
 采用 Java 语言实现，运行在服务器端。
 负责处理游戏规则、协调玩家之间的对战。 客户端
 采用 Java 语言实现，运行在玩家的电脑上。
 采用 Socket 协议进行不同玩家间的通信。
 客户端主要包括以下模块：
o 游戏界面模块：负责显示游戏画面、用户界面等内容。
o 游戏逻辑模块：负责处理用户操作、向后端服务发送请求等。 系统流程
1. 玩家启动客户端，连接登录服务。
2. 玩家登录认证，获取访问令牌。
3. 玩家选择游戏模式（人机对战或多人对战）。
4. 玩家进入游戏大厅，输入匹配码与对方 IP，等待游戏开始。
5. 游戏大厅服务根据匹配规则，将玩家匹配到对手。
6. 玩家进入游戏房间，与对手进行实时对战。
实验报告
7. 游戏结束后，玩家可以返回游戏大厅。 程序架构：
main
  java
    com
      entity
        AdminDo//登录确认类
          handler//Action 事件文件包
          LoginHandler//登录 Action 事件控制文件
          MainViewHandler//游戏界面 Action 事件控制文件
          MenuHandler///游戏大厅 Action 事件控制文件
          message//消息控制文件包
        Message//消息类
          MessageReceive//消息接收控制文件
          MessageSend//消息发送控制文件
          MessageStart//开始进行消息控制文件
        service//Service 层
          impl
            AdminServiceImpl//登录接口实现类
        AdminService//登录接口
      User//用户控制包
        Opponent//对手敌手类
        Player//玩家类
      Util//工具包
        DimensionUtil//界面窗口工具类
        MathUtil//数学工具类
        OpponentUtil//对手控制工具类
        PlayerUtil//游戏工具类
      view//视图包
        LoginView//登录视图类
        MainView//游戏视图类
        MenuView//游戏大厅视图
      resource
        META-INF
        MANIFEST.MF//打包资源
        background.gif//棋盘图片
        blackStone.gif//黑棋图片
        img34.jpg//loginView 图标
        keqing.png//窗口图标（羞耻）
        log4j.properties//日志配置文件
        whiteStone.gif//白棋图片
        pom.xml//Maven 依赖配置文件
3
