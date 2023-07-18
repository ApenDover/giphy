package ts.andrey.giphy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ts.andrey.giphy.rest.dto.CourseResponse;

@FeignClient(value = "course", url = "${openexchangerates.url.general}")
public interface CourseClient {

    @GetMapping("/latest.json?app_id=${openexchangerates.app.id}&base=${openexchangerates.currency}")
    CourseResponse courseToday();

    @GetMapping("/historical/{date}.json?app_id=${openexchangerates.app.id}")
    CourseResponse courseHistory(@PathVariable String date);

}
