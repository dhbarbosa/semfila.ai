package ai.semfila.api.service;

import ai.semfila.api.DTO.Product.ProductRequest;
import ai.semfila.api.DTO.Product.ProductResponse;
import ai.semfila.api.model.Product;
import ai.semfila.api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    private ModelMapper mapper;


    public Product findById(String id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElse(null);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public List<ProductResponse> findAll() {
        List<Product> result = productRepository.findAll();
        List<ProductResponse> response = result.stream().map(ProductResponse::new).toList();
        return response ;
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> update(ProductRequest productRequest) {

        return Optional.of(productRepository.save(mapper.map(productRequest, Product.class)));
    }
}
