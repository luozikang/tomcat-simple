package org.lzk.process;


import org.apache.commons.net.io.SocketInputStream;
import org.lzk.connector.HttpConnector;
import org.lzk.http.HttpRequest;
import org.lzk.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: lzk
 * @version:
 * @date:2019/11/16 00:14
 * @description:
 */
public class HttpProcessor {
    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;
    public HttpProcessor(HttpConnector httpConnector) {
        this.connector = httpConnector;
    }
    public void process(Socket socket){
        SocketInputStream in=null;
        OutputStream out = null;
        try {
            in = new SocketInputStream(socket,socket.getInputStream());
            out= socket.getOutputStream();

            request= new HttpRequest(in);
            response= new HttpResponse(out);
            response.setRequest(request);



            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
