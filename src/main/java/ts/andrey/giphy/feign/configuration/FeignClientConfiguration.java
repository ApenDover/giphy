package ts.andrey.giphy.feign.configuration;

import feign.Client;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    @ConditionalOnProperty(name = "giphy.feign.logger.enabled", havingValue = "true")
    public Client feignClient() {
        return new LoggingFeignClient();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }

}
