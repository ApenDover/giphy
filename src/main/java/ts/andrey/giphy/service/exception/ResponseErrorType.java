package ts.andrey.giphy.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseErrorType {

    ERROR(301, "Transfer not found"),

    SUCCESS(200, "Success");

    private final int code;

    private final String message;

    public static ResponseErrorType valueOf(int codeProperty) {
        for (ResponseErrorType value : values()) {
            if (codeProperty == value.code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Illegal value of TransferErrorType code: " + codeProperty);
    }

}
