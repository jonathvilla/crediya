package co.com.authentication.r2dbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;

import co.com.authentication.model.user.User;
import co.com.authentication.r2dbc.entity.UserEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {

    @InjectMocks
    MyReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    MyReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    @Test
    void mustFindByUserId_whenUserExists() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUserId("u123");

        User user = new User();
         user.setUserId("jeeehjsgfk");
        user.setUserId("u123");

        when(repository.findByUserId("u123")).thenReturn(Mono.just(entity));
        when(mapper.map(entity, User.class)).thenReturn(user);

        StepVerifier.create(repositoryAdapter.findByUserId("u123"))
                .expectNextMatches(u -> u.getUserId().equals("u123"))
                .verifyComplete();
    }

    @Test
    void mustFindByUserId_whenUserDoesNotExist() {
        when(repository.findByUserId("notfound")).thenReturn(Mono.empty());

        StepVerifier.create(repositoryAdapter.findByUserId("notfound"))
                .verifyComplete();
    }

    @Test
    void mustFindByEmail_whenUserExists() {
        UserEntity entity = new UserEntity();
        entity.setId(2L);
        entity.setEmail("test@mail.com");

        User user = new User();
        user.setUserId("jhjsgfk");
        user.setEmail("test@mail.com");

        when(repository.findByEmail("test@mail.com")).thenReturn(Mono.just(entity));
        when(mapper.map(entity, User.class)).thenReturn(user);

        StepVerifier.create(repositoryAdapter.findByEmail("test@mail.com"))
                .expectNextMatches(u -> u.getEmail().equals("test@mail.com"))
                .verifyComplete();
    }

    @Test
    void mustFindByEmail_whenUserDoesNotExist() {
        when(repository.findByEmail("nope@mail.com")).thenReturn(Mono.empty());

        StepVerifier.create(repositoryAdapter.findByEmail("nope@mail.com"))
                .verifyComplete();
    }

    @Test
    void mustReturnTrueIfExistsByEmail() {
        UserEntity entity = new UserEntity();
        entity.setEmail("exists@mail.com");

        when(repository.findByEmail("exists@mail.com")).thenReturn(Mono.just(entity));

        StepVerifier.create(repositoryAdapter.existsByEmail("exists@mail.com"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void mustReturnFalseIfEmailDoesNotExist() {
        when(repository.findByEmail("ghost@mail.com")).thenReturn(Mono.empty());

        StepVerifier.create(repositoryAdapter.existsByEmail("ghost@mail.com"))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void mustFindByDocumentNumber_whenExists() {
        UserEntity entity = new UserEntity();
        entity.setDocumentNumber("12345");

        User user = new User();
        user.setDocumentNumber("12345");

        when(repository.findByDocumentNumber("12345")).thenReturn(Mono.just(entity));
        when(mapper.map(entity, User.class)).thenReturn(user);

        StepVerifier.create(repositoryAdapter.findByDocumentNumber("12345"))
                .expectNextMatches(u -> u.getDocumentNumber().equals("12345"))
                .verifyComplete();
    }

    @Test
    void mustReturnTrueIfExistsByDocumentNumber() {
        UserEntity entity = new UserEntity();
        entity.setDocumentNumber("12345");

        when(repository.findByDocumentNumber("12345")).thenReturn(Mono.just(entity));

        StepVerifier.create(repositoryAdapter.existsByDocumentNumber("12345"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void mustReturnFalseIfDocumentNumberDoesNotExist() {
        when(repository.findByDocumentNumber("54321")).thenReturn(Mono.empty());

        StepVerifier.create(repositoryAdapter.existsByDocumentNumber("54321"))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void mustDeleteById() {
        when(repository.deleteById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(repositoryAdapter.deleteById(99L))
                .verifyComplete();
    }

    @Test
    void mustPropagateErrorWhenDeleteFails() {
        when(repository.deleteById(100L)).thenReturn(Mono.error(new RuntimeException("DB error")));

        StepVerifier.create(repositoryAdapter.deleteById(100L))
                .expectErrorMatches(err -> err instanceof RuntimeException && err.getMessage().equals("DB error"))
                .verify();
    }
}
