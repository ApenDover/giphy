package ts.andrey.giphy.rest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyResponse {

    private Data data;

    private Meta meta;


    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        private String type;

        private String id;

        private String url;

        private String slug;

        @JsonAlias("bitly_gif_url")
        private String bitlyGifUrl;

        @JsonAlias("bitly_url")
        private String bitlyUrl;

        @JsonAlias("embed_url")
        private String embedUrl;

        private String username;

        private String source;

        private String title;

        private String rating;

        @JsonAlias("content_url")
        private String contentUrl;

        @JsonAlias("source_tld")
        private String sourceTld;

        @JsonAlias("source_post_url")
        private String sourcePostUrl;

        @JsonAlias("is_sticker")
        private int isSticker;

        @JsonAlias("import_datetime")
        private String importDatetime;

        @JsonAlias("trending_datetime")
        private String trendingDatetime;

        private Images images;

        private User user;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SizeGiphy {

        private String frames;

        private String hash;

        private String height;

        private String mp4;

        @JsonAlias("mp4_size")
        private String mp4Size;

        private String size;

        private String url;

        private String webp;

        @JsonAlias("webp_size")
        private String webpSize;

        private String width;

    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Images {
        private SizeGiphy hd;

        @JsonAlias("fixed_width_still")
        private SizeGiphy fixedWidthStill;

        @JsonAlias("fixed_height_downsampled")
        private SizeGiphy fixedHeightDownsampled;

        @JsonAlias("preview_gif")
        private SizeGiphy previewGif;

        private SizeGiphy preview;

        @JsonAlias("fixed_height_small")
        private SizeGiphy fixedHeightSmall;

        private SizeGiphy downsized;

        @JsonAlias("fixed_width_downsampled")
        private SizeGiphy fixedWidthDownsampled;

        @JsonAlias("fixed_width")
        private SizeGiphy fixedWidth;

        @JsonAlias("downsized_still")
        private SizeGiphy downsizedStill;

        @JsonAlias("downsized_medium")
        private SizeGiphy downsizedMedium;

        @JsonAlias("original_mp4")
        private SizeGiphy originalMp4;

        @JsonAlias("downsized_large")
        private SizeGiphy downsizedLarge;

        @JsonAlias("preview_webp")
        private SizeGiphy previewWebp;

        private SizeGiphy original;

        @JsonAlias("original_still")
        private SizeGiphy originalStill;

        @JsonAlias("fixed_height_small_still")
        private SizeGiphy fixedHeightSmallStill;

        @JsonAlias("fixed_width_small")
        private SizeGiphy fixedWidthSmall;

        private SizeGiphy looping;

        @JsonAlias("downsized_small")
        private SizeGiphy downsizedSmall;

        @JsonAlias("fixed_width_small_still")
        private SizeGiphy fixedWidthSmallStill;

        @JsonAlias("fixed_height_still")
        private SizeGiphy fixedHeightStill;

        @JsonAlias("fixed_height")
        private SizeGiphy fixedHeight;

        @JsonAlias("_480w_still")
        private SizeGiphy wStill;
    }


    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {

        private String msg;

        private int status;

        @JsonAlias("response_id")
        private String responseId;

    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {

        @JsonAlias("avatar_url")
        private String avatarUrl;

        @JsonAlias("banner_image")
        private String bannerImage;

        @JsonAlias("banner_url")
        private String bannerUrl;

        @JsonAlias("profile_url")
        private String profileUrl;

        private String username;

        @JsonAlias("display_name")
        private String displayName;

        private String description;

        @JsonAlias("is_verified")
        private boolean isVerified;

        @JsonAlias("website_url")
        private String websiteUrl;

        @JsonAlias("instagram_url")
        private String instagramUrl;

    }

}
