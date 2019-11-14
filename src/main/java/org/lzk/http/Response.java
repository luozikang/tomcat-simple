package org.lzk.http;

import org.lzk.server.HttpServer;
import org.lzk.server.Request;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author lzk
 * @description
 * @date 2019/11/14
 */
public class Response implements ServletResponse {

    private static final String FILE_NOT_FIND_MESSAGE = "HTTP/1.1 404 File Not Found \nContent-Type: text/html\nContent-Length: 23\n\n<h1>File Not Found";

    private static final int BUFFER_SIZE = 1024;
    OutputStream out;
    PrintWriter writer ;


    private org.lzk.server.Request request;
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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(writer==null) {
            this.writer = new PrintWriter(out, true);
        }
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
