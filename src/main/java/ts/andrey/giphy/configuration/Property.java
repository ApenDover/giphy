package ts.andrey.giphy.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Property {

    @Value("${openexchangerates.currency}")
    private String baseCurrency;

}
