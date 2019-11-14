package org.lzk.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lzk
 *
 * 简版处理静态资源WEB服务器
 */
public class HttpServer {
    /**
     * web服务资源路径
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUTOWN_COMMAD = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
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
        while (!shutdown) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try {
                socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();
                Request request = new Request(in);
                request.parse();

                Response response = new Response(out);
                response.setReqest(request);
                response.sendStaticResource();

                socket.close();
                shutdown = request.getUri().equals(SHUTOWN_COMMAD);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}
