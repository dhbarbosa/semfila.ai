package ai.semfila.api.controller;


import ai.semfila.api.DTO.user.UserRequest;
import ai.semfila.api.DTO.user.UserResponse;
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

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public ResponseEntity<Page<UserResponse>> index(@PageableDefault() Pageable pageable) {
        var allUser = service.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest){
        var newUser = service.save(new User(userRequest));
        return ResponseEntity.status(HttpStatus.OK).body(newUser) ;
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<User> findById */

}
