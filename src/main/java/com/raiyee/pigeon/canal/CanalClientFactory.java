package com.raiyee.pigeon.canal;

import com.raiyee.pigeon.config.TaskConfig;

/**
 * 获取对应的 Canal Client。
 *
 * @author wanglei
 * @date 17/1/20 下午3:07
 */
public class CanalClientFactory {

    public static CanalClient getClient(TaskConfig config) {
        CanalClient canalClient = new CanalClient(config);
        return canalClient;
    }
}
