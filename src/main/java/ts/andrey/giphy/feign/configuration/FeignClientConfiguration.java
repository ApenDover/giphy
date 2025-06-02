package ts.andrey.giphy.feign.configuration;

import feign.Client;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    @ConditionalOnProperty(name = "giphy.feign-logger.enabled", havingValue = "true")
    public Client feignClient() {
        return new LoggingFeignClient();
    }

}
