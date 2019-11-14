package org.lzk.process;


import org.lzk.server.Request;
import org.lzk.server.Response;

/**
 * @author lzk
 * @description
 * @date 2019/11/14
 */
public class ServletProcessor implements Processor {


    @Override
    public void processor(Request request, Response response) {
        String uri = request.getUri();

    }
}
