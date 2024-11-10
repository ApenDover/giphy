package ts.andrey.giphy.exception;

public class GiphyTransportException extends RuntimeException {

    public GiphyTransportException(String message) {
        super(message);
    }

    public GiphyTransportException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiphyTransportException(Throwable cause) {
        super(cause);
    }

    public GiphyTransportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
