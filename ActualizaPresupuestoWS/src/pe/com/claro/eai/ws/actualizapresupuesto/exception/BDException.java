package pe.com.claro.eai.ws.actualizapresupuesto.exception;


public class BDException extends BaseException{
    
    private static final long serialVersionUID = -1036484449123606033L;

    public BDException(String string) {
        super(string);
    }

    public BDException(Exception exception) {
        super(exception);
    }

    public BDException(String sp, Exception exception) {
        super(sp, exception);
    }

    public BDException(String code, String message, Exception exception) {
        super(code, message, exception);
    }

    public BDException(String code, String message) {
        super(code, message);
    }
    
}
