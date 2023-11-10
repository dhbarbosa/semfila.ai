package ai.semfila.api.controller;


import ai.semfila.api.DTO.user.*;
import ai.semfila.api.model.User;
import ai.semfila.api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public ResponseEntity<Page<UserResponse>> index(@PageableDefault() Pageable pageable) {
        var allUser = service.findAll(pageable).map(UserResponse::new);
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest){
        var newUser = this.service.save(new User(userRequest));
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(newUser)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id){
        var user = service.findById(id);
        return methodResponse(user);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponse> findByCpf(@PathVariable String cpf){
        var user = service.findByCpf(cpf);

        return methodResponse(user);

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody @Valid UserRequestUpdate userRequestUpdate){
        var user = this.service.update(id, userRequestUpdate);

        return methodResponse(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID id ){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }



    private static ResponseEntity<UserResponse> methodResponse(Optional<User> user) {
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new UserResponse(user.get()));
    }


}
