package co.com.authentication.model.user.exeptions;

public class InvalidSalaryException extends BusinessException {
    public InvalidSalaryException(ErrorCodes errorCode) {
        super(errorCode);
    }
}