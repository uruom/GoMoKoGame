package com.message;

public class Message {
    public  static String data;

    public static String getData() {
        return data;
    }

    public static void setData(int row ,int col) {
        Message.data = row +","+col;
    }
}
