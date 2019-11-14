package org.lzk.process;


import org.lzk.process.Processor;
import org.lzk.server.Request;
import org.lzk.server.Response;

import java.io.IOException;

/**
 * @author lzk
 * @description
 * @date 2019/11/14
 */
public class StaticResourceProcessor implements Processor {


    @Override
    public void processor(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
