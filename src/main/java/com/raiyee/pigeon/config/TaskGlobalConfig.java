package com.raiyee.pigeon.config;

/**
 * CanalClient 全局配置。
 *
 * @author wanglei
 * @date 17/1/19 下午4:48
 */
public class TaskGlobalConfig {
    private int batchSize;

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public String toString() {
        return "TaskGlobalConfig{" +
                "batchSize=" + batchSize +
                '}';
    }
}
