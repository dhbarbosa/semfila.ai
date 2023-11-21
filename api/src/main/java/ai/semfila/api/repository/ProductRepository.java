package ai.semfila.api.repository;

import ai.semfila.api.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Port;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    @Query("SELECT product FROM Product product WHERE product.deleted = false")
    Page<Product> findAll(Pageable pageable);

    @Override
    @Query("SELECT product FROM Product product WHERE product.deleted = false and product.id = ?1 ")
    Optional<Product> findById(@Param("id")UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Product product SET product.deleted = true WHERE product.id = ?1 ")
    void delete(@Param("id") UUID id);
}
