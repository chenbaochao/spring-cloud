package com.play001.cloud.zuul;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new BestAvailableRule();
    }

}
