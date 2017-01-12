package com.raiyee.pigeon.canal;

import com.alibaba.otter.canal.protocol.ClientIdentity;
import com.alibaba.otter.canal.server.embedded.CanalServerWithEmbedded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于 canal embed 实现数据获取。
 *
 * @author wanglei
 * @date 17/1/12 下午4:16
 */
public class CanalEmbedServer {
    private static final Logger LOG = LoggerFactory.getLogger(CanalEmbedServer.class);

    private CanalServerWithEmbedded canalServer;
    private ClientIdentity clientIdentity;

    private String destination;
    private String filter;
    private int batchSize = 1000;
    private long batchTimeout = -1L;
    private boolean ddlSync = false;
    private boolean filterTableError = false;

    private volatile boolean running = false;

    public CanalEmbedServer() {
        this.canalServer = new CanalServerWithEmbedded();
    }

}
