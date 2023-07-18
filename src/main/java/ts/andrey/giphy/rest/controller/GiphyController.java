package ts.andrey.giphy.rest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;
import ts.andrey.giphy.service.ActualGiphyByExchangeRateService;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class GiphyController {

    private final ActualGiphyByExchangeRateService actualGiphyByExchangeRateService;

    @ResponseBody
    @RequestMapping("/getGiphy")
    public GiphyServiceResponse getGiphy(@RequestParam("currencyCode") int currencyNumber) {
        try {
            MDC.put("request", " - " + UUID.randomUUID());
            log.info("Got request for currency {}", currencyNumber);
            return actualGiphyByExchangeRateService.getResponse(currencyNumber);
        } finally {
            MDC.clear();
        }
    }


    @GetMapping("/")
    public String getIndex(Model model) {
        final var giphyResponse = getGiphy(643);
        model.addAttribute("url", giphyResponse.getGiphyUrl());
        model.addAttribute("rateYesterday", giphyResponse.getRateAmountYesterday());
        model.addAttribute("rateToday", giphyResponse.getRateAmountToday());
        return "index";
    }

}
