package co.com.authentication.usecase.registeruser;

import co.com.authentication.model.user.exeptions.BusinessException;
import co.com.authentication.model.user.exeptions.ErrorCodes;

public class InvalidSalaryException extends BusinessException {
    public InvalidSalaryException(ErrorCodes errorCode) {
        super(errorCode);
    }
}