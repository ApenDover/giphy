package ts.andrey.giphy.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_ERROR("Internal error"),

    CURRENCY_NOT_FOUND("Currency not found"),

    RATE_IS_NOT_AVAILABLE_NOW("actual rate is not available");

    private final String message;

}
