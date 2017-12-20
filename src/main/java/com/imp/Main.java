package com.imp;

import com.imp.task.MyTimerTask;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static Socket socket = null; // 客户端连接器
    private static final int PORT = 30105;
    public static void main(String args[]){

        try {
            // 连接服务器
            // 如果服务器未启动则抛异常
            (socket = new Socket("localhost", PORT)).close();
            // 如果服务器已经启动则退出系统
            JOptionPane.showMessageDialog(null, " 系统已经启动，不能重复启动！ ", " 提示消息 ", JOptionPane.ERROR_MESSAGE);
            //logger.error("提示信息：不能重复启动！");
            System.exit(0);
        } catch (Exception e) {
        }// 未做处理
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyTimerTask.getInstance();
    }
}
