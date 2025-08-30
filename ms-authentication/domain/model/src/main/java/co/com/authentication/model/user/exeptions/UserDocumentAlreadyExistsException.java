package co.com.authentication.model.user.exeptions;

public class UserDocumentAlreadyExistsException extends BusinessException {
    public UserDocumentAlreadyExistsException() {
        super(ErrorCodes.DOCUMENT_EXISTS);
    }
}
