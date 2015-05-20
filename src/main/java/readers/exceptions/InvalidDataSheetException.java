package readers.exceptions;

public class InvalidDataSheetException extends DataReaderException {

    private static final long serialVersionUID = 1L;
    
    public InvalidDataSheetException(String message) {
        super(message);
    }
}
