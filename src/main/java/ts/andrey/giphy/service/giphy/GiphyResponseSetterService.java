package ts.andrey.giphy.service.giphy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;
import ts.andrey.giphy.service.exception.ResponseErrorType;
import ts.andrey.giphy.utils.ResponseStatusSetterUtil;
import ts.andrey.giphy.utils.GiphyLinkHashUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiphyResponseSetterService {

    private final GiphyService giphyService;

    public GiphyServiceResponse setGiphy(GiphyServiceResponse giphyServiceResponse) {

        if (giphyServiceResponse.getRateAmountToday() < giphyServiceResponse.getRateAmountYesterday()) {
            giphyServiceResponse.setGiphyMood("rich");
            log.info("get rich giphy");
            String link;
            try {
                link = giphyService.getLinkOnRich().getData().getImages().getDownsizedMedium().getUrl();
                GiphyLinkHashUtil.addRichLink(link);
                giphyServiceResponse.setGiphyUrl(link);
            } catch (Exception e) {
                log.error(e.getMessage());
                link = GiphyLinkHashUtil.getRichHash();
                if (Strings.isEmpty(link)) {
                    ResponseStatusSetterUtil.setStatus(giphyServiceResponse, ResponseErrorType.ERROR);
                    log.error(e.getMessage(), e);
                } else {
                    log.error("response link from cash");
                    giphyServiceResponse.setGiphyUrl(link);
                    giphyServiceResponse.setCash(true);
                }
            }
        } else {
            giphyServiceResponse.setGiphyMood("broke");
            log.info("get broke giphy");
            String link;
            try {
                link = giphyService.getLinkOnBroke().getData().getImages().getDownsizedMedium().getUrl();
                GiphyLinkHashUtil.addBrokeLink(link);
                giphyServiceResponse.setGiphyUrl(link);
            } catch (Exception e) {
                log.error("get link from cash: {}", e.getMessage(), e);
                link = GiphyLinkHashUtil.getBrokeHash();
                if (Strings.isEmpty(link)) {
                    ResponseStatusSetterUtil.setStatus(giphyServiceResponse, ResponseErrorType.ERROR);
                    log.error(e.getMessage(), e);
                } else {
                    giphyServiceResponse.setGiphyUrl(link);
                    giphyServiceResponse.setCash(true);
                }
            }
        }

        return giphyServiceResponse;
    }

}
