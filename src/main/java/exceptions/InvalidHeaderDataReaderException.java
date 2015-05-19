package exceptions;


public class InvalidHeaderDataReaderException extends DataReaderException {

    private static final long serialVersionUID = 1L;

    public InvalidHeaderDataReaderException(String message) {
        super(message);
    }

}
