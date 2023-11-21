package ai.semfila.api.service;

import ai.semfila.api.DTO.product.ProductUpdate;
import ai.semfila.api.DTO.user.UserUpdateRequest;
import ai.semfila.api.model.Product;
import ai.semfila.api.model.User;
import ai.semfila.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public Page<Product> findAll(Pageable pageable) {
       return this.repository.findAll(pageable);
    }

    public Product save(Product product) {
        return this.repository.save(product);
    }

    public Optional<Product> findById(UUID id) {
        return this.repository.findById(id);
    }
    @Transactional
    public Optional<Product> update(ProductUpdate productUpdate, UUID id) {
        var product = repository.findById(id);
        if(product.isEmpty()){
            return  product;
        }
        product.get().update(productUpdate);
        return  product;
    }


    public void delete(UUID id) {
        this.repository.delete(id);
    }
}
