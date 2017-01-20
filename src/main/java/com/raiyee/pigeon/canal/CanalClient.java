package com.raiyee.pigeon.canal;

import com.raiyee.pigeon.PigeonController;
import com.raiyee.pigeon.config.TaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Canal Client.
 *
 * @author wanglei
 * @date 17/1/20 下午3:01
 */
public class CanalClient {
    private static final Logger LOG = LoggerFactory.getLogger(PigeonController.class);
    private TaskConfig taskConfig;

    public CanalClient(TaskConfig taskConfig) {
        this.taskConfig = taskConfig;
    }

}
