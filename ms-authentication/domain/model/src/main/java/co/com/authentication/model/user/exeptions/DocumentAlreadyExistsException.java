package co.com.authentication.model.user.exeptions;

public class DocumentAlreadyExistsException extends BusinessException {
    public DocumentAlreadyExistsException() {
        super(ErrorCodes.DOCUMENT_EXISTS);
    }
}
