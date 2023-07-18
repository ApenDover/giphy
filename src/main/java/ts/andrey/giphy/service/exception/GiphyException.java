package ts.andrey.giphy.service.exception;


public class GiphyException extends RuntimeException {

    public static final long serialVersionUID = 123347569287346L;

    public GiphyException(ErrorType errorType) {
        super(errorType.getMessage());
    }

    public GiphyException(String message) {
        super(message);
    }


}
