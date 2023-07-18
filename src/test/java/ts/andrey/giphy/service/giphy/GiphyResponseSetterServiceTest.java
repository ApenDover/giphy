package ts.andrey.giphy.service.giphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.andrey.giphy.rest.dto.GiphyResponse;
import ts.andrey.giphy.rest.dto.GiphyServiceResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiphyResponseSetterServiceTest {

    @Mock
    private transient GiphyService giphyService;

    private transient GiphyResponseSetterService service;

    private transient GiphyServiceResponse giphyServiceResponse = new GiphyServiceResponse();

    private transient GiphyResponse giphyResponse = new GiphyResponse();

    private static final String URL = "URL";

    @BeforeEach
    void setup() {
        service = new GiphyResponseSetterService(giphyService);
        final var medium = new GiphyResponse.SizeGiphy();
        final var images = new GiphyResponse.Images();
        final var data = new GiphyResponse.Data();
        medium.setUrl(URL);
        images.setDownsizedMedium(medium);
        data.setImages(images);
        giphyResponse.setData(data);
    }

    @Test
    void successSetGiphyShouldReturnRichUrlFromGiphyService() {
        //GIVEN
        giphyServiceResponse.setRateAmountToday(2);
        giphyServiceResponse.setRateAmountYesterday(3);
        when(giphyService.getLinkOnRich()).thenReturn(giphyResponse);

        //WHEN
        service.setGiphy(giphyServiceResponse);

        //THEN
        assertEquals("rich", giphyServiceResponse.getGiphyMood());
        assertEquals(URL, giphyServiceResponse.getGiphyUrl());
    }

    @Test
    void successSetGiphyShouldReturnBrokeUrlFromGiphyService() {
        //GIVEN
        giphyServiceResponse.setRateAmountToday(3);
        giphyServiceResponse.setRateAmountYesterday(2);
        when(giphyService.getLinkOnBroke()).thenReturn(giphyResponse);

        //WHEN
        service.setGiphy(giphyServiceResponse);

        //THEN
        assertEquals("broke", giphyServiceResponse.getGiphyMood());
        assertEquals(URL, giphyServiceResponse.getGiphyUrl());
    }

}
