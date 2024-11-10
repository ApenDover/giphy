package ts.andrey.giphy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ts.andrey.giphy.feign.configuration.FeignClientConfiguration;
import ts.andrey.giphy.rest.dto.GiphyResponse;

@FeignClient(name = "giphy-client",
        url = "${giphy.url.general}",
        configuration = FeignClientConfiguration.class
)
public interface GiphyClient {

    @GetMapping("/random?api_key=${giphy.api.key}&tag=${giphy.courseUp}&rating=r")
    GiphyResponse getLinkRich();

    @GetMapping("/random?api_key=${giphy.api.key}&tag=${giphy.courseDown}&rating=r")
    GiphyResponse getLinkBroke();

}
