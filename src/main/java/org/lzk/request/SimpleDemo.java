package org.lzk.request;

import java.io.*;
import java.net.Socket;

/**
 * @author lzk
 * @description
 * @date 2019/11/12
 */
public class SimpleDemo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("139.9.234.241", 28083);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            boolean autoFlash= true;
            PrintWriter pw = new PrintWriter(outputStream,autoFlash);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            pw.println("GET /pages/home.html HTTP/1.1");
            pw.println("Connection: Close");
            pw.println("Host: scshuangliu.wengetech.com:28083");
            pw.println("Cache-Control: max-age=0");
            pw.println("Upgrade-Insecure-Requests: 1");
            pw.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
            pw.println("Referer: http://scshuangliu.wengetech.com:8081/pages/index.html");
            pw.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            pw.println("Accept-Encoding: gzip, deflate, br");
            pw.println("Accept-Language: zh-CN,zh;q=0.9,en;q=0.8");
            pw.println("Cookie: SESSION=MTViYjYwYjItNDgxMi00YTZhLWJmMmItYzg5Njg5NTI3MzNl; TGC=T-dc5c9237-621c-4bd4-ba04-9182ee4c68a9-42C9EAE7E9AC707A4804E2B6C949E86A-c6a358ff-c572-423e-9bc9-50d8d6cabbd0; IAM-SESSION=T-dc5c9237-621c-4bd4-ba04-9182ee4c68a9-42C9EAE7E9AC707A4804E2B6C949E86A-c6a358ff-c572-423e-9bc9-50d8d6cabbd0");
            pw.println();


            boolean loop = true;
            StringBuilder sb = new StringBuilder();
            int i = 0;

            while (loop){
                if(in.ready()){
                    while (i!=-1){
                        i = in.read();
                        sb.append((char) i);
//                        System.out.print((char)i);
//                        Thread.sleep(10);

                    }
                    loop =  false;
                }
                Thread.sleep(50);
            }
            System.out.println(sb.toString());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
