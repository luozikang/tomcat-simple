package org.lzk.http;

import org.apache.commons.net.io.SocketInputStream;

/**
 * @author: lzk
 * @version:
 * @date:2019/11/16 00:23
 * @description:
 */
public class HttpRequest {
    SocketInputStream stream;
    public HttpRequest(SocketInputStream stream) {
        this.stream = stream;
    }
}
