package co.com.authentication.model.user.exeptions;

public abstract class BusinessException extends RuntimeException {
    
    private final ErrorCodes errorCode;

    protected BusinessException(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}