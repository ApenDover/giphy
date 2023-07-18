package ts.andrey.giphy.service.giphy;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ts.andrey.giphy.feign.GiphyClient;
import ts.andrey.giphy.rest.dto.GiphyResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiphyService {

    private final GiphyClient giphyClient;

    public GiphyResponse getLinkOnRich() throws FeignException {
        final GiphyResponse giphyResponse;
        giphyResponse = giphyClient.getLinkRich();
        return giphyResponse;
    }

    public GiphyResponse getLinkOnBroke() throws FeignException {
        final var brokeLink = giphyClient.getLinkBroke();
        return brokeLink;
    }

}
