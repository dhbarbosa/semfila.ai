package ai.semfila.api.service;

import ai.semfila.api.DTO.user.UserResponse;
import ai.semfila.api.model.User;
import ai.semfila.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<UserResponse> findAll( Pageable pageable) {
        return this.repository.findAll(pageable).map(UserResponse::new);
    }


    public UserResponse save(User user){
        return new UserResponse(repository.save(user));
    }
}
