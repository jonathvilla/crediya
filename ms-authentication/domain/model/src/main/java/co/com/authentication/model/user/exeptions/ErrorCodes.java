package co.com.authentication.model.user.exeptions;


public enum ErrorCodes {
    UNDER_AGE("USER_UNDER_AGE", "El usuario debe ser mayor de edad"),
    SALARY_NEGATIVE("USER_SALARY_NEGATIVE", "El salario base debe ser mayor a 0"),
    SALARY_EXCEEDS("USER_SALARY_EXCEEDS", "El salario base no puede exceder 15,000,000"),
    EMAIL_EXISTS("USER_EMAIL_EXISTS", "Correo electrónico ya registrado"),
    DOCUMENT_EXISTS("USER_DOCUMENT_EXISTS", "Número de documento ya registrado");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}