package com.raiyee.pigeon.config;

/**
 * CanalServer 配置信息.
 *
 * @author wanglei
 * @date 17/1/19 下午3:53
 */
public class CanalServerConfig {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "CanalServerConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
