package com.raiyee.pigeon;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Pigeon 启动入口。
 *
 * @author wanglei
 * @date 17/1/12 下午2:53
 */
public class PigeonLauncher {
    private static final String CLASSPATH_URL_PREFIX = "classpath:";
    private static final Logger LOG = LoggerFactory.getLogger(PigeonLauncher.class);

    public static void main(String[] args) {
        try {
            Properties properties = loadLaunchConf();
            final PigeonController controller = startupController(properties);
            addShutdownHook(controller);
        } catch (Throwable throwable) {
            LOG.error("something goes wrong when starting up the pigeon server:\n{}",
                    ExceptionUtils.getFullStackTrace(throwable));
            System.exit(0);
        }
    }

    private static PigeonController startupController(Properties properties) {
        LOG.info("start the pigeon server.");
        final PigeonController controller = new PigeonController(properties);
        controller.start();
        LOG.info("the pigeon server is running now ......");
        return controller;
    }

    private static Properties loadLaunchConf() throws IOException {
        String conf = System.getProperty("pigeon.conf", "classpath:pigeon.properties");
        Properties properties = new Properties();
        if (conf.startsWith(CLASSPATH_URL_PREFIX)) {
            conf = StringUtils.substringAfter(conf, CLASSPATH_URL_PREFIX);
            properties.load(PigeonLauncher.class.getClassLoader().getResourceAsStream(conf));
        } else {
            properties.load(new FileInputStream(conf));
        }
        return properties;
    }

    private static void addShutdownHook(PigeonController controller) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                LOG.info("stop the pigeon server");
                controller.stop();
            } catch (Throwable throwable) {
                LOG.warn("something goes wrong when stopping pigeon server:\n{}",
                        ExceptionUtils.getFullStackTrace(throwable));
            } finally {
                LOG.info("pigeon server is down.");
            }
        }));
    }

}
