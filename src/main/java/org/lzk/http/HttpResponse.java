package org.lzk.http;

import java.io.OutputStream;

/**
 * @author: lzk
 * @version:
 * @date:2019/11/16 00:23
 * @description:
 */
public class HttpResponse {
    OutputStream out;
    private HttpRequest request
            ;

    public HttpResponse(OutputStream out) {
        this.out= out;
    }

    public void setRequest(HttpRequest request) {
        this.request= request;
    }
}
