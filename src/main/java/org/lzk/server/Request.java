package org.lzk.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author lzk
 *
 */
public class Request {
    private InputStream in;
    private String uri;

    public Request(InputStream in) {
        this.in = in;
    }

    /**
     * 解析请求头
     */
    public void parse() {
        StringBuilder sb = new StringBuilder(2048);

        /**
         * 请求头长度不能超出2048个字节。
         */
        byte[] buff = new byte[2048];
        try {
            in.read(buff)
        } catch (IOException e) {
            e.printStackTrace();

        }
        for (int j = 0; j < buff.length; j++) {
            sb.append((char) buff[j]);
        }
        String result = sb.toString();
        System.out.println(result);
        uri = parseUri(result);
    }

    /**
     * 解析请求头
     *
     * @param result
     * @return
     */
    private String parseUri(String result) {
        Objects.requireNonNull(result);
        String[] s = result.substring(0, result.indexOf("\n")).split(" ");
        if (s.length > 1) {
            return s[1];
        }
        return "";
    }

    public String getUri() {
        return uri;
    }
}
