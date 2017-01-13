package com.raiyee.pigeon;

import com.alibaba.otter.canal.instance.core.CanalInstance;
import com.alibaba.otter.canal.instance.core.CanalInstanceGenerator;
import com.alibaba.otter.canal.server.embedded.CanalServerWithEmbedded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Document Me!!
 *
 * @author wanglei
 * @date 17/1/13 下午4:08
 */
public class CanalEmbedServer {
    private static final Logger LOG = LoggerFactory.getLogger(CanalEmbedServer.class);
    private PigeonTask task;
    private CanalServerWithEmbedded canalServer;

    public CanalEmbedServer(PigeonTask task) {
        this.task = task;
        canalServer = new CanalServerWithEmbedded();
    }

    public void start() {
        canalServer.setCanalInstanceGenerator(new CanalInstanceGenerator() {
            @Override
            public CanalInstance generate(String destination) {
                return null;
            }
        });
    }


}
