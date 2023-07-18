package ts.andrey.giphy.service.giphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.andrey.giphy.feign.GiphyClient;
import ts.andrey.giphy.rest.dto.GiphyResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiphyServiceTest {

    @Mock
    private transient GiphyClient giphyClient;

    private transient GiphyService service;

    private final transient GiphyResponse giphyResponse = new GiphyResponse();

    private static final transient String URL = "ActualUrl";

    @BeforeEach
    void setup() {
        service = new GiphyService(giphyClient);
        final var medium = new GiphyResponse.SizeGiphy();
        final var images = new GiphyResponse.Images();
        final var data = new GiphyResponse.Data();
        medium.setUrl(URL);
        images.setDownsizedMedium(medium);
        data.setImages(images);
        giphyResponse.setData(data);
    }

    @Test
    void getLinkOnRich() {
        //GIVEN
        when(giphyClient.getLinkRich()).thenReturn(giphyResponse);

        //WHEN
        var actual = service.getLinkOnRich();

        //THEN
        assertEquals(URL, actual.getData().getImages().getDownsizedMedium().getUrl());
    }

    @Test
    void getLinkOnBroke() {
        //GIVEN
        when(giphyClient.getLinkBroke()).thenReturn(giphyResponse);

        //WHEN
        var actual = service.getLinkOnBroke();

        //THEN
        assertEquals(URL, actual.getData().getImages().getDownsizedMedium().getUrl());
    }

}
