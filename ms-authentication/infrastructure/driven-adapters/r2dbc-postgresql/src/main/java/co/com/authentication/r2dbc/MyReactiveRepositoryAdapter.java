package co.com.authentication.r2dbc;

import co.com.authentication.model.user.User;
import co.com.authentication.model.user.gateways.UserRepository;
import co.com.authentication.r2dbc.entity.UserEntity;
import co.com.authentication.r2dbc.helper.ReactiveAdapterOperations;
import reactor.core.publisher.Mono;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
    User/* change for domain model */,
    UserEntity/* change for adapter model */,
    Long,
    MyReactiveRepository
> implements UserRepository {
    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class/* change for domain model */));
    }

    @Override
    public Mono<User> findByUserId(String userId) {
        return repository.findByUserId(userId)
                .map(entity -> mapper.map(entity, User.class));
    }

   
    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<User> findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber)
                .map(entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
         return repository.findByEmail(email)
                     .hasElement();
    }

    @Override
    public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber)
                     .hasElement();
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

}
