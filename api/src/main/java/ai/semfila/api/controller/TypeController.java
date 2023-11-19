package ai.semfila.api.controller;

import ai.semfila.api.DTO.type.TypeRequest;
import ai.semfila.api.DTO.type.TypeResponse;
import ai.semfila.api.model.Type;
import ai.semfila.api.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/type")
public class TypeController {
    private final TypeService typeService;

/*    @PostMapping()
    public ResponseEntity<TypeResponse> save(@RequestBody @Valid TypeRequest typeRequest){
        var type = this.typeService.save(new Type(typeRequest));
        return ResponseEntity.ok(new TypeResponse(type));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> findById(@PathVariable UUID id){
        var type = this.typeService.fidById(id);
        return methodResponse(type);
    }

    @GetMapping("/")
    public ResponseEntity<Page<TypeResponse>> index(@PageableDefault()Pageable pageable){
        var allTypes = this.typeService.findAll(pageable).map(TypeResponse::new);
        return ResponseEntity.ok().body(allTypes);
    }


    private static ResponseEntity<TypeResponse> methodResponse(Optional<Type> type) {
        return type.map(value -> ResponseEntity.ok().body(new TypeResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

