package com.raiyee.pigeon;

import com.alibaba.otter.canal.server.embedded.CanalServerWithEmbedded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * TODO: Document Me!!
 *
 * @author wanglei
 * @date 17/1/12 下午5:49
 */
public class PigeonController {
    private static final Logger LOG = LoggerFactory.getLogger(PigeonController.class);
    private CanalServerWithEmbedded canalServer;

    public PigeonController(Properties properties) {
        // TODO: 加载 Task 列表、并依次执行
    }

    private PigeonTask getTask() {
        PigeonTask task = new PigeonTask();
        task.setId(1);
        task.setName("demo");
        task.setDestination("demo");
        task.setFilter(".*\\..*");
        task.setBatchSize(1000);
        return task;
    }

    public void start() {
        PigeonTask task = getTask();
        CanalEmbedServer canalEmbedServer = new CanalEmbedServer(task);
        canalEmbedServer.start();
    }

    public void stop() {

    }
}
