package com.raiyee.pigeon.config;

/**
 * CanalClient 配置信息.
 *
 * @author wanglei
 * @date 17/1/19 下午4:03
 */
public class TaskConfig {
    private TaskGlobalConfig globalConfig;
    private String id;
    private String name;
    private String filter;
    private String destination;
    private int batchSize;
    private boolean disabled;
    private String rabbitQueue;

    public TaskConfig(TaskGlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getBatchSize() {
        if (batchSize <= 0 && globalConfig != null) {
            return globalConfig.getBatchSize();
        } else {
            return batchSize;
        }
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getRabbitQueue() {
        return rabbitQueue;
    }

    public void setRabbitQueue(String rabbitQueue) {
        this.rabbitQueue = rabbitQueue;
    }

    @Override
    public String toString() {
        return "TaskConfig{" +
                "globalConfig=" + globalConfig +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", filter='" + filter + '\'' +
                ", destination='" + destination + '\'' +
                ", batchSize=" + batchSize +
                ", disabled=" + disabled +
                ", rabbitQueue='" + rabbitQueue + '\'' +
                '}';
    }
}
