package com.raiyee.pigeon;

import com.raiyee.pigeon.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * Pigeon 控制器。
 *
 * @author wanglei
 * @date 17/1/12 下午5:49
 */
public class PigeonController {
    private static final Logger LOG = LoggerFactory.getLogger(PigeonController.class);
    private final CanalServerConfig canalServerConfig;
    private final RabbitMQConfig rabbitMQConfig;
    private final TaskGlobalConfig taskGlobalConfig;
    private final Map<String, TaskConfig> taskConfigs;

    private ExecutorService executor;

    public PigeonController(Properties properties) {
        this.canalServerConfig = ConfigInitiator.initCanalServerConfig(properties);
        this.rabbitMQConfig = ConfigInitiator.initRabbitMQConfig(properties);
        this.taskGlobalConfig = ConfigInitiator.initTaskGlobalConfig(properties);
        try {
            this.taskConfigs = ConfigInitiator.initTaskConfigs(this.taskGlobalConfig);
            if (CollectionUtils.isEmpty(this.taskConfigs)) {
                throw new RuntimeException("not found task config files");
            }
        } catch (Exception e) {
            LOG.error("init task config failed.", e);
            throw new RuntimeException(e);
        }
    }

    public void start() {
    }

    public void stop() {
    }

}
