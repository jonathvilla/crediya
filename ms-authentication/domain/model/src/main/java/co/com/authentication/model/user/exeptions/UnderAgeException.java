package co.com.authentication.model.user.exeptions;

public class UnderAgeException extends BusinessException {
     public UnderAgeException() {
        super(ErrorCodes.UNDER_AGE);
    }
}

