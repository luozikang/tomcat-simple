package org.lzk.process;


import org.lzk.http.Request;
import org.lzk.http.Response;
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
