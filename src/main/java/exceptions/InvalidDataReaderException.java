package exceptions;

public class InvalidDataReaderException extends DataReaderException {

    private static final long serialVersionUID = 1L;

    public InvalidDataReaderException() {
        super();
    }
    
    public InvalidDataReaderException(String message) {
        super(message);
    }
    
}
