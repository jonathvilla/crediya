package co.com.authentication.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.authentication.model.user.User;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Configuration
public class RouterRest {
     @Bean
    @RouterOperations({
        @RouterOperation(
            path = "/api/v1/usuarios",
            produces = { "application/json" },
            method = RequestMethod.POST,
            beanClass = Handler.class,
            beanMethod = "registerUser",
            operation = @Operation(
                operationId = "registerUser",
                summary = "Crear usuario",
                tags = { "User API" },
                requestBody = @RequestBody(
                    required = true,
                    description = "Datos del nuevo usuario",
                    content = @Content(schema = @Schema(implementation = User.class))
                ),
                responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario creado", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Error en la petición"),
                    @ApiResponse(responseCode = "500", description = "Error interno")
                }
            )
        )
    })
    
   
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
            return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase)
                .andRoute(POST("/api/v1/usuarios"), handler::registerUser));
    }
}
