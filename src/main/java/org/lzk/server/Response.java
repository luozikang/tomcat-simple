package org.lzk.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private static final String FILE_NOT_FIND_MESSAGE = "HTTP/1.1 404 File Not Found \nContent-Type: text/html\nContent-Length: 23\n\n<h1>File Not Found";

    private static final int BUFFER_SIZE = 1024;
    OutputStream out;

    private Request request;
    private static final String HSUCCESS_HEADER = "HTTP/1.1 200 OK\n" +
            "Connection: keep-alive\n" +
            "Content-Type: text/html; charset=utf-8\n\n";

    public Response(OutputStream out) {
        this.out = out;
    }

    public void setReqest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {

        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {


            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                //该响应头信息必须存在
                out.write(HSUCCESS_HEADER.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    out.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                out.write(FILE_NOT_FIND_MESSAGE.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
