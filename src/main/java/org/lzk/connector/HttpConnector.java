package org.lzk.connector;

import org.lzk.process.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: lzk
 * @version: http 连接器,负责处理http请求
 * @date:2019/11/16 00:09
 * @description:
 */
public class HttpConnector implements Runnable{
    boolean stoped;
    String schema="http";

    public String getSchema() {
        return schema;
    }

    @Override
    public void run() {
        ServerSocket serverSocket=null;
        try {
            serverSocket= new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!stoped){
            Socket socket = null;
            try {
                socket=serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            HttpProcessor processor=new HttpProcessor(this);
            processor.process(socket);
        }
    }


    public void start() {
        new Thread(this).start();
    }
}
