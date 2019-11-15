package org.lzk.startup;

import org.lzk.connector.HttpConnector;

/**
 * @author: lzk
 * @version:
 * @date:2019/11/16 00:08
 * @description:
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}