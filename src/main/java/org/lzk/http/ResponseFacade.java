package org.lzk.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Response对象门面类
 * 包装某一类对象，防止该对象不想被暴露的公开方法通过强转的方式被调用。
 * 强转后获取不到被包装的对象。
 * 防止Resopnse中的sendStaticresource()方法，
 * 在传递到Servlet的service中通过强转被使用，因为Request对象是公开的，parseUri方法也是公开的
 */
public class ResponseFacade implements ServletResponse {
    Response response;

    public ResponseFacade(Response response) {
        this.response = response;
    }

    @Override
    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return response.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    @Override
    public void setCharacterEncoding(String s) {
        response.setCharacterEncoding(s);
    }

    @Override
    public void setContentLength(int i) {
        response.setContentLength(i);
    }

    @Override
    public void setContentType(String s) {
        response.setContentType(s);
    }

    @Override
    public void setBufferSize(int i) {
        response.setBufferSize(i);
    }

    @Override
    public int getBufferSize() {
        return response.getBufferSize();
    }

    @Override
    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        response.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return response.isCommitted();
    }

    @Override
    public void reset() {
        response.reset();
    }

    @Override
    public void setLocale(Locale locale) {
        response.setLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return response.getLocale();
    }
}
