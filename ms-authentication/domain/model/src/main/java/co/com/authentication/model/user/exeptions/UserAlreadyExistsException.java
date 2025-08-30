package co.com.authentication.model.user.exeptions;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException() {
        super(ErrorCodes.EMAIL_EXISTS);
    }
}