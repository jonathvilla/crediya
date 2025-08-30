package co.com.authentication.usecase.registeruser;

import java.time.LocalDateTime;
import java.util.UUID;

import co.com.authentication.model.user.User;
import co.com.authentication.model.user.exeptions.DocumentAlreadyExistsException;
import co.com.authentication.model.user.exeptions.ErrorCodes;
import co.com.authentication.model.user.exeptions.UnderAgeException;
import co.com.authentication.model.user.exeptions.UserAlreadyExistsException;
import co.com.authentication.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(User user) {
        return validateUserData(user)
                .then(checkUserDoesNotExist(user))
                .then(createUser(user));
    }

    private Mono<Void> validateUserData(User user) {
        if (!user.isValidAge()) {
            return Mono.error(new UnderAgeException());
        }

        if (user.getBaseSalary() == null || user.getBaseSalary() < 0) {
            return Mono.error(new InvalidSalaryException(ErrorCodes.SALARY_NEGATIVE));
        }

        if (user.getBaseSalary() > 15_000_000) {
            return Mono.error(new InvalidSalaryException(ErrorCodes.SALARY_EXCEEDS));
        }

        return Mono.empty();
    }

    private Mono<Void> checkUserDoesNotExist(User user) {
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(emailExists -> {
                    if (emailExists) {
                        return Mono.error(new UserAlreadyExistsException());
                    }

                    return userRepository.existsByDocumentNumber(user.getDocumentNumber())
                            .flatMap(docExists -> {
                                if (docExists) {
                                    return Mono.error(new DocumentAlreadyExistsException());
                                }
                                return Mono.empty();
                            });
                });
    }

    private Mono<User> createUser(User user) {
        User newUser = user.toBuilder()
                .userId(UUID.randomUUID().toString())
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        return userRepository.save(newUser);
    }
}
