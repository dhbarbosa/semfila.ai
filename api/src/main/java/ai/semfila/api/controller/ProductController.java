package ai.semfila.api.controller;

import ai.semfila.api.DTO.company.CompanyResponse;
import ai.semfila.api.DTO.product.ProductRequest;
import ai.semfila.api.DTO.product.ProductResponse;
import ai.semfila.api.DTO.product.ProductUpdate;
import ai.semfila.api.model.Company;
import ai.semfila.api.model.Product;
import ai.semfila.api.service.ProductService;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
@AllArgsConstructor
public class ProductController {
    private ProductService service;
    private AbstractComponentTracker<Object> product;

    @GetMapping("/")
    public ResponseEntity<Page<ProductResponse>> index(@PageableDefault() Pageable pageable){
        var products = this.service.findAll(pageable).map(ProductResponse::new);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest productRequest, UriComponentsBuilder uriBuilder){
        var newProduct = this.service.save(new Product(productRequest));
        URI uri = uriBuilder.path("/v1/product/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductResponse(newProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id){
        var product = this.service.findById(id);
        return methodResponse(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductUpdate productUpdate, @PathVariable UUID id){
        var product = this.service.update(productUpdate, id);
        return methodResponse(product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    private static ResponseEntity<ProductResponse> methodResponse(Optional<Product> product) {
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new ProductResponse(product.get()));
    }


}
