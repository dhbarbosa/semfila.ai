package ai.semfila.api.service;

import ai.semfila.api.DTO.user.UserRequestUpdate;
import ai.semfila.api.DTO.user.UserResponse;
import ai.semfila.api.model.User;
import ai.semfila.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<User> findAll( Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public User save(User user){
        return this.repository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return this.repository.findById(id);

    }

    public Optional<User> findByCpf(String cpf){
        return this.repository.findByCpf(cpf);
    }

    public void delete(UUID uuid){
        this.repository.delete(uuid);
    }

    @Transactional
    public Optional<User> update(UUID id, UserRequestUpdate userRequestUpdate) {
        var user = repository.findById(id);
        if(repository.findById(id).isEmpty()){
           return  user;
        }

        user.get().update(userRequestUpdate);
        return  user;
    }

}
