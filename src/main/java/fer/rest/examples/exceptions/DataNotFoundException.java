package fer.rest.examples.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg){
        super(msg);
    }
}
