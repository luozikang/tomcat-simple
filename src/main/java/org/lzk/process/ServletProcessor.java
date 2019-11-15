package org.lzk.process;


import org.lzk.constant.Constants;
import org.lzk.http.ReqeustFacade;
import org.lzk.http.Request;
import org.lzk.http.Response;
import org.lzk.http.ResponseFacade;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author lzk
 * @description
 * @date 2019/11/14
 */
public class ServletProcessor implements Processor {


    @Override
    public void processor(Request request, Response response) {
        String uri = request.getUri();
        String servletName = "org.lzk.servlet."+uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();

            urls[0] = new URL(null, repository, streamHandler);
            /**
             * urlClassLoader 会加载urls路径下的类，
             * 若ruls 以File.sparator结尾，将会加载该目录及子目录下的所有类，
             * 若ruls 中不宜File.sparator结尾则指向jar包 ，将会加载该jar包.
             */
            loader = new URLClassLoader(urls);

            URLClassLoader
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        Class servletClass = null;
        try {
            servletClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




        Servlet servlet = null;
        try {
            servlet = (Servlet) servletClass.newInstance();
            servlet.service(new ReqeustFacade(request), new ResponseFacade(response));


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


//        URLConnection urlConnection = urls[0].openConnection();

    }
}
