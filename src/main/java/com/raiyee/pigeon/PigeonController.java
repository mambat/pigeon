package com.raiyee.pigeon;

import com.google.common.collect.MapMaker;
import com.raiyee.pigeon.canal.CanalTask;
import com.raiyee.pigeon.config.ConfigInitiator;
import com.raiyee.pigeon.config.TaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Properties;

/**
 * Pigeon 控制器。
 * <p>
 * TODO:
 * 1) 检测并执行新增的 task 配置文件。
 * 2) 优化 task 配置文件加载方式
 *
 * @author wanglei
 * @date 17/1/12 下午5:49
 */
public class PigeonController {
    private static final Logger LOG = LoggerFactory.getLogger(PigeonController.class);
    private final Properties config;
    private final Map<String, TaskConfig> taskConfigs;
    private final Map<String, CanalTask> canalTasks;

    public PigeonController(Properties properties) {
        this.config = properties;
        this.canalTasks = new MapMaker().makeMap();
        try {
            this.taskConfigs = ConfigInitiator.init(this.config);
            if (CollectionUtils.isEmpty(this.taskConfigs)) {
                throw new RuntimeException("task config files not exists");
            }
        } catch (Exception e) {
            LOG.error("init task config failed.", e);
            throw new RuntimeException(e);
        }
    }

    public void start() {
        for (Map.Entry<String, TaskConfig> entry : taskConfigs.entrySet()) {
            CanalTask canalTask = new CanalTask(entry.getKey(), entry.getValue());
            canalTasks.put(entry.getKey(), canalTask);
            canalTask.start();
        }
    }

    public void stop() {
        if (CollectionUtils.isEmpty(canalTasks)) {
            return;
        }

        for (Map.Entry<String, CanalTask> entry : canalTasks.entrySet()) {
            try {
                entry.getValue().shutdown();
            } catch (Exception e) {
                LOG.error("shutdown task<{}> error", entry.getKey(), e);
            }
        }
    }

}
