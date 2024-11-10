package ts.andrey.giphy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ts.andrey.giphy.feign.configuration.FeignClientConfiguration;
import ts.andrey.giphy.rest.dto.CourseResponse;

@FeignClient(name = "course-client",
        url = "${openexchangerates.url.general}",
        configuration = FeignClientConfiguration.class
)
public interface CourseClient {

    @GetMapping("/latest.json?app_id=${openexchangerates.app.id}&base=${openexchangerates.currency}")
    CourseResponse courseToday();

    @GetMapping("/historical/{date}.json?app_id=${openexchangerates.app.id}")
    CourseResponse courseHistory(@PathVariable String date);

}
