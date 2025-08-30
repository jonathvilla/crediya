package co.com.authentication.model.user.exeptions;

public class SalaryNegativeException extends BusinessException{
    public SalaryNegativeException() {
        super(ErrorCodes.SALARY_NEGATIVE);
    }
    
}
