package pe.com.claro.eai.ws.actualizapresupuesto.exception;


public class BaseException extends Exception{
    
    private static final long serialVersionUID = 6626369279397701163L;
    
    private Exception objException;
    private String code;
    private String message;
    private String sp;

    public BaseException(){}
    
    public BaseException(String code, String message, Exception objException) {
        super(message);
        this.objException = objException;
        this.code = code;
        this.message = message;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String sp, Exception objException) {
        super(sp);
        this.objException = objException;
        this.sp = sp;
    }
    
    public BaseException(Exception exception) {
        this.objException = exception;
    }
    
    public BaseException(String message) {
        super(message);
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getObjException() {
        return objException;
    }
    public void setObjException(Exception objException) {
        this.objException = objException;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }
    
}
