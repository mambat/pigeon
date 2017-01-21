package com.raiyee.pigeon.config;

import com.raiyee.pigeon.utils.Props;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.raiyee.pigeon.PigeonConstants.*;

/**
 * 配置初始化器。
 *
 * @author wanglei
 * @date 17/1/20 下午2:25
 */
public class ConfigInitiator {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigInitiator.class);

    public static Map<String, TaskConfig> init(Properties properties) throws IOException {
        CanalServerConfig canalServerConfig = initCanalServerConfig(properties);
        RabbitMQConfig rabbitMQConfig = initRabbitMQConfig(properties);
        TaskGlobalConfig taskGlobalConfig = initTaskGlobalConfig(properties);
        Map<String, TaskConfig> taskConfigs = initTaskConfigs();

        for (Map.Entry<String, TaskConfig> entry : taskConfigs.entrySet()) {
            entry.getValue().setGlobalConfig(taskGlobalConfig);
            entry.getValue().setCanalServerConfig(canalServerConfig);
            entry.getValue().setRabbitMQConfig(rabbitMQConfig);
        }
        return taskConfigs;
    }

    private static CanalServerConfig initCanalServerConfig(Properties properties) {
        CanalServerConfig canalServerConfig = new CanalServerConfig();
        canalServerConfig.setHost(Props.getString(properties, CANAL_SERVER_HOST, "127.0.0.1"));
        canalServerConfig.setPort(Props.getInt(properties, CANAL_SERVER_PORT, 11111));
        LOG.info("CanalServer config: {}", canalServerConfig);
        return canalServerConfig;
    }

    private static RabbitMQConfig initRabbitMQConfig(Properties properties) {
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();
        rabbitMQConfig.setHost(Props.getString(properties, RABBITMQ_HOST, "127.0.0.1"));
        rabbitMQConfig.setPort(Props.getInt(properties, RABBITMQ_PORT, 5672));
        rabbitMQConfig.setUser(Props.getString(properties, RABBITMQ_USER));
        rabbitMQConfig.setPass(Props.getString(properties, RABBITMQ_PASS));
        LOG.info("RabbitMQ config: {}", rabbitMQConfig);
        return rabbitMQConfig;
    }

    private static TaskGlobalConfig initTaskGlobalConfig(Properties properties) {
        TaskGlobalConfig taskGlobalConfig = new TaskGlobalConfig();
        taskGlobalConfig.setBatchSize(Props.getInt(properties, TASK_GLOBAL_BATCH_SIZE, 100));
        LOG.info("CanalClient global config: {}", taskGlobalConfig);
        return taskGlobalConfig;
    }

    private static Map<String, TaskConfig> initTaskConfigs() throws IOException {
        final String taskConfigPath = "classpath:task/*.properties";
        Map<String, TaskConfig> taskConfigMap = new HashMap<>();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(taskConfigPath);
        for (Resource resource : resources) {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            TaskConfig taskConfig = initTaskConfig(properties);

            if (taskConfigMap.containsKey(taskConfig.getId())) {
                LOG.error("task id reduplication: {}", taskConfig);
                throw new RuntimeException("task id reduplication");
            }
            taskConfigMap.put(taskConfig.getId(), taskConfig);
        }
        return taskConfigMap;
    }

    private static TaskConfig initTaskConfig(Properties taskProps) {
        TaskConfig taskConfig = new TaskConfig();
        String id = Props.getString(taskProps, TASK_ID);
        taskConfig.setId(id);
        if (StringUtils.isEmpty(id)) {
            LOG.error("task id is required: {}", taskProps);
            throw new RuntimeException("task id is required");
        }

        taskConfig.setName(Props.getString(taskProps, TASK_NAME));

        String filter = Props.getString(taskProps, TASK_FILTER);
        taskConfig.setFilter(filter);
        if (StringUtils.isEmpty(filter)) {
            LOG.error("task filter is required: {}", taskProps);
            throw new RuntimeException("task filter is required");
        }

        String destination = Props.getString(taskProps, TASK_DESTINATION);
        taskConfig.setDestination(destination);
        if (StringUtils.isEmpty(destination)) {
            LOG.error("task destination is required: {}", taskProps);
            throw new RuntimeException("task destination is required");
        }

        taskConfig.setBatchSize(Props.getInt(taskProps, TASK_BATCH_SIZE));

        String rabbitQueue = Props.getString(taskProps, TASK_RABBIT_QUEUE);
        taskConfig.setRabbitQueue(rabbitQueue);
        if (StringUtils.isEmpty(rabbitQueue)) {
            LOG.error("task rabbitQueue is required: {}", taskProps);
            throw new RuntimeException("task rabbitQueue is required");
        }

        taskConfig.setDisabled(Props.getBoolean(taskProps, TASK_DISABLED, false));
        return taskConfig;
    }

}
