package ai.semfila.api.controller;

import ai.semfila.api.DTO.Product.ProductRequest;
import ai.semfila.api.DTO.Product.ProductResponse;
import ai.semfila.api.model.Product;
import ai.semfila.api.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> result = productService.findAll();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable ("id") String id){
        return ResponseEntity.ok().body(mapper.map(productService.findById(id), ProductResponse.class));
    }
    @PostMapping()
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product productCreate = this.productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductRequest> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest){
        productRequest.setId(id);
      return ResponseEntity.ok().body(mapper.map(this.productService.update(productRequest), ProductRequest.class)) ;

    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") String id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
