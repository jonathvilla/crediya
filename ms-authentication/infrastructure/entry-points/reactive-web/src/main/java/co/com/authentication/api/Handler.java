package co.com.authentication.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.authentication.model.user.User;
import co.com.authentication.usecase.registeruser.RegisterUserUseCase;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;

     private final RegisterUserUseCase registerUserUseCase;

    public Mono<ServerResponse> registerUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> {
                    log.info("Iniciando registro de usuario: {}", user.getEmail());
                    return registerUserUseCase.execute(user)
                            .doOnSuccess(u -> log.info("Usuario registrado exitosamente: {}", u.getUserId()))
                            .doOnError(e -> log.error("Error al registrar usuario: {}", e.getMessage()));
                })
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(error ->
                        ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
