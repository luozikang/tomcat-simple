package org.lzk.process;


import org.lzk.http.Request;
import org.lzk.http.Response;

public interface Processor  {
    public void processor(Request request, Response response);
}
