package org.lzk.process;

import org.lzk.server.Request;
import org.lzk.server.Response;

public interface Processor  {
    public void processor(Request request, Response response);
}
