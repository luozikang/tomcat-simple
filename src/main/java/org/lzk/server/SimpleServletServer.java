package org.lzk.server;

import org.lzk.constant.Constants;
import org.lzk.http.Request;
import org.lzk.http.Response;
import org.lzk.process.ServletProcessor;
import org.lzk.process.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lzk
 */
public class SimpleServletServer {
    public boolean shutdown = false;

    public static void main(String[] args) {
        SimpleServletServer container = new SimpleServletServer();
        container.await();
    }


    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
        }

        while (true) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try {
                socket = serverSocket.accept();

                in = socket.getInputStream();
                out = socket.getOutputStream();

                org.lzk.http.Request request = new Request(in);
                request.parse();

                org.lzk.http.Response response = new Response(out);
                response.setReqest(request);

                /**
                 * 系统退出
                 */
                if (request.getUri().equals(Constants.SHUTOWN_COMMAD)) {
                    break;
                }

                if (request.getUri().contains("/servlet")) {
                    new ServletProcessor().processor(request, response);
                } else {
                    new StaticResourceProcessor().processor(request, response);
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}
