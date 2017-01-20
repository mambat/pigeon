package com.raiyee.pigeon;

/**
 * 常量集合。
 *
 * @author wanglei
 * @date 17/1/12 下午5:50
 */
public interface PigeonConstants {
    String CANAL_SERVER_HOST = "canal.server.host";
    String CANAL_SERVER_PORT = "canal.server.port";

    String TASK_GLOBAL_BATCH_SIZE = "task.global.batchSize";

    String RABBITMQ_HOST = "rabbitmq.host";
    String RABBITMQ_PORT = "rabbitmq.port";
    String RABBITMQ_USER = "rabbitmq.user";
    String RABBITMQ_PASS = "rabbitmq.pass";

    String TASK_ID = "task.id";
    String TASK_NAME = "task.name";
    String TASK_FILTER = "task.filter";
    String TASK_DESTINATION = "task.destination";
    String TASK_BATCH_SIZE = "task.batchSize";
    String TASK_RABBIT_QUEUE = "task.rabbitQueue";
    String TASK_DISABLED = "task.disabled";
}
