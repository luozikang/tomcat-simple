package org.lzk.request;

import java.io.*;
import java.net.Socket;

/**
 * @author lzk
 * @description
 * @date 2019/11/11
 */
public class SocketClient {
    public void request(String ip, int port) throws IOException, InterruptedException {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream, true);
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(in);

        pw.println("GET /system/main/index HTTP/1.1");
        pw.println("Connection: keep-alive");
        pw.println("Host: 92.168.16.15:9191");
        pw.println("Cache-Control: max-age=0");
        pw.println("Upgrade-Insecure-Requests: 1");
        pw.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
        pw.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        pw.println("Accept-Encoding: gzip, deflate, br");
        pw.println("Accept-Language: zh-CN,zh;q=0.9,en;q=0.8");
        pw.println("Cookie: mmwikissid=1fbd50a2b8e65bfd312716bc043a8a8d; mmwikipassport=k8sKnEWIgdCRr7rXyducgEhcizgAgFvDgF6zudrFuFrZhd9Fu4vAudaC");
        pw.println();
        StringBuilder sb = new StringBuilder(1024);
        String s = "";

        while (true) {
            if (in.ready()) {
                while((s = br.readLine())!=null){
                    sb.append(s);
                }
                System.out.println("over");
                break;
            }
            Thread.sleep(500);
        }

        System.out.println(sb.toString());

        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new SocketClient().request("192.168.16.15", 9191);
    }


}
