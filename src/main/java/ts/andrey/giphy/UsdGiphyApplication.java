package ts.andrey.giphy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"ts.andrey.giphy.feign"})
public class UsdGiphyApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsdGiphyApplication.class, args);
    }

}
