package com.yit.gitprd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: clive
 * @date: 2018/06/07
 * @since: 1.0
 */
public class SystemUtils {

    private static final Logger logger = LoggerFactory.getLogger(SystemUtils.class);

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * 在finder中打开指定路径
     *
     * @param path
     */
    public static void openInFinder(String path) {
        logger.info("openInFinder: path={}", path);
        Assert.isTrue(StringUtil.isNotBlank(path), "路径为空不能打开");
        Assert.isTrue(FileUtil.exist(path), "路径不存在: " + path);
        try {
            String command = "open " + path;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            logger.error("openInFinder error: path=" + path, e);
        }
    }

    /**
     * 用默认浏览器打开
     *
     * @param url
     */
    public static void browserUrl(String url){
        if (Desktop.isDesktopSupported()){
            try {
                URI uri = new URI(url);
                Desktop desktop = Desktop.getDesktop(); //创建desktop对象
                //调用默认浏览器打开指定URL
                desktop.browse(uri);
            } catch (Exception e) {
                logger.error("browserUrl error: url=" + url, e);
            }
        } else {
            throw new IllegalArgumentException("桌面不支持打开此地址");
        }
    }


}
