package spring;

import javax.annotation.Resource;

/**
 * Service
 * <p/>
 * Copyright &copy; 2013 by Konstantin Pavlov
 *
 * @author Konstantin Pavlov
 */
@org.springframework.stereotype.Service
public class MyService {

    @Resource
    private Config config;

    public void printConfig() {
        System.out.printf("Started %s at %s", config.getProduct(), config.getEnvironment());
        System.out.printf("Settings:\n%s\n", config.getSettings());
    }
}
