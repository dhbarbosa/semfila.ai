package ai.semfila.api.service;

import ai.semfila.api.model.Type;
import ai.semfila.api.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Type save(Type type) {
        return this.typeRepository.save(type);
    }

    public Optional<Type> fidById(UUID id) {
        return this.typeRepository.findById(id);
    }

    public Page<Type> findAll(Pageable pageable) {
        return this.typeRepository.findAll(pageable);
    }
}
