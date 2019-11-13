package org.lzk.client;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author lzk
 * @description
 * @date 2019/11/11
 */
public class SocketClient {
    public void request(String ip, int port) throws IOException, InterruptedException {
        Socket socket = ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(ip, port);

        boolean connected = socket.isConnected();

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//        "Connection: Close\n" + 该头信息为close流才会关闭，流不会返回，程序会处于阻塞阶段
        pw.print("GET /lzkmyname/tomcat-simple HTTP/1.0\n" +
                "Host: github.com\n" +
                "Connection: Close\n" +
                "Cache-Control: max-age=0\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36\n" +
                "Sec-Fetch-User: ?1\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\n" +
                "Sec-Fetch-Site: none\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8\n" +
                "Cookie: _octo=GH1.1.919538659.1573569384; _ga=GA1.2.1960935461.1573569387; tz=Asia%2FShanghai; _device_id=6edd2849cf43f61eef15f54757e2d3fd; user_session=e3R4CKtzNeQIFKXcOz0ZEfJ8ooNaDpRrCkA0rz-w6xxpkka0; __Host-user_session_same_site=e3R4CKtzNeQIFKXcOz0ZEfJ8ooNaDpRrCkA0rz-w6xxpkka0; logged_in=yes; dotcom_user=lzkmyname; _gh_sess=eU9BSTRxLy81eHVScG0xZXFRL1VUYzdYWGxjQmlyalZ2azdUcUZyR1p1ZU5yenN3M3c4Z0IyaW1UVktUczY0RVNTTTlCNWRGK2dlaElHblBmeUg2WVI3azhDMEZYUzdMd0xhSTZBVFIrdElkdy9sNWozN3BxR2F6bitWcjkwMW1xZlJpUHV6M3dJdTNmNjNCMytBTTgwbEt0a2JDZm9MY0FFUE9jZVBsbVE1b1NFV2x1bVR4YldXNEJuZ3V2VGg4bkl3Vy9GWTk4Z21CWEduUllVUXQxNjE4eVBwU0FidE0yN2V1eS9jOXBwck5KK2N5NnJmUHp3aWQ4amIxYmdqVGhtS21Dc0RwbDh1RmpyQTF3d0wvbGREQTZDUzIxUTVQM2s3dWZGY0MvZVp2eXVzMHU0Slk5U2Vsb1BCSTl4dTQzWld4YXZKYlk2T3JiOC9JRUduTWk4Z0RldnNzMmx5V2hSdVNFd09lZ3dZPS0tVUVqWVNzc0ZuUEJSVjRUa0tGbXcrUT09--5027978fd67670707532362226f55a19827326f3\n" +
                "If-None-Match: W/\"595460f007d68fd83e43606a16218a8b\"\n");
        pw.println();//该回车换行必须
        pw.flush();

        boolean loop = true;
        StringBuilder sb = new StringBuilder(1024);


        while (loop) {
            if (br.ready()) {
                int s = 0;
                while (s != -1) {
                    s = br.read();
                    sb.append((char) s);
                }
                loop = false;
            }
            Thread.currentThread().sleep(50);
        }

        System.out.println(sb.toString());

        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        new SocketClient().request("52.74.223.119", 443);
        new SocketClient().request("52.74.223.119", 443);
//        System.out.println((int)'\uFFFF');
//        new SocketClient().request("localhost", 8080);
    }


}
