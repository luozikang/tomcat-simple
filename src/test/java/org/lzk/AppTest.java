package org.lzk;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.lzk.constant.Constants;
import org.lzk.server.HttpServer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public void sayHello(){
        System.out.println("appTest Hello");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ServletException {

        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();

            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        Class<HttpServer> aClass = (Class<HttpServer>) loader.loadClass("org.lzk.server.HttpServer");
        HttpServer server= aClass.newInstance();
        server.await();
    }


}
