package com.raiyee.pigeon.canal;

import com.raiyee.pigeon.config.TaskConfig;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Canal BinLog 拉取和处理线程。
 *
 * @author wanglei
 * @date 17/1/21 上午10:27
 */
public class CanalTask extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(CanalTask.class);
    private boolean running = true;
    private final String taskName;
    private final TaskConfig taskConfig;

    public CanalTask(String taskName, TaskConfig taskConfig) {
        this.taskName = taskName;
        this.taskConfig = taskConfig;
    }

    @Override
    public void run() {
        try {
            while (running) {
                try {
                    startup();
                } catch (Throwable t) {
                    if (isInterrupt(t)) {
                        LOG.info("task is interrupt", t);
                        return;
                    } else {
                        LOG.warn("task<{}> is failed, will retry in 10s", this.taskName, t);
                        try {
                            // sleep 10秒再进行重试
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        } finally {
            // TODO: release resources
        }
    }

    private void startup() {
        // TODO: start fetch binlog
    }

    private boolean isInterrupt(Throwable t) {
        if (!running) {
            return true;
        }
        if (t instanceof InterruptedException) {
            return true;
        }
        if (ExceptionUtils.getRootCause(t) instanceof InterruptedException) {
            return true;
        }
        return false;
    }

    public void shutdown() {
        this.running = false;
        interrupt();
    }

}
