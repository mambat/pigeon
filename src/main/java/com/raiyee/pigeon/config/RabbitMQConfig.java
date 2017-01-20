package com.raiyee.pigeon.config;

/**
 * RabbitMQ 配置信息.
 *
 * @author wanglei
 * @date 17/1/19 下午3:28
 */
public class RabbitMQConfig {
    private String host;
    private int port;
    private String user;
    private String pass;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "RabbitMQConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
