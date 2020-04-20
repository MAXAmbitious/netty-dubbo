package com.beidao.netty.dubbo.zk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beidao.netty.dubbo.constants.GlobalConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author beidao
 *
 */
@Service
public class ZookeeperConfig {
    private final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);
    private int timeOut;
    private String dataPath;
    private String registryPath;
    private int serverPort;
    private String serverHost;

    private int elapsedTimeMs;
    private Properties properties;


    public void init() {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(GlobalConstants.ZOOKEEPER_PROPERTIES_FILE_PATH);
        try {
            properties = new Properties();
            properties.load(in);
            timeOut = Integer.parseInt(properties.getProperty("zookeeper.timeout"));
            dataPath = properties.getProperty("zookeeper.data.path");
            registryPath = properties.getProperty("zookeeper.registry.path");
            serverPort = Integer.parseInt(properties.getProperty("zookeeper.port"));
            serverHost = properties.getProperty("zookeeper.ip");
            elapsedTimeMs = Integer.parseInt(properties.getProperty("zookeeper.elapsedTimeMs"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        }
    }

    public String getRegistryAddress() {
        return this.getServerHost() + ":" + this.getServerPort();
    }

    public String getRegistryPath() {
        return registryPath;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getElapsedTimeMs() {
        return elapsedTimeMs;
    }

}

