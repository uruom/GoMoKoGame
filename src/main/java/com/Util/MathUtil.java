package com.Util;

public class MathUtil {
    public static int smin(int a,int b){
        if(a>b) return b;
        else return a;
    }
    public static int smax(int a,int b){
        if(a>b) return a;
        else return b;
    }
    public static int stringToInt(String s){
        int n = s.length();
        int tempNum = 0;
        char tempChar;
        for(int i=0;i<n;i++){
            tempChar = s.charAt(i);
            if(tempChar>='0'&&tempChar<='9'){
                tempNum*=10;
                tempNum+=tempChar-'0';
            }
        }
        return tempNum;
    }



}
