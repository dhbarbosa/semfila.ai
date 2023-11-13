package ai.semfila.api.repository;

import ai.semfila.api.model.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Override
    @Query("SELECT company FROM Company company WHERE company.deleted = false")
    Page<Company> findAll(Pageable pageable);

    @Query("SELECT company FROM Company company WHERE company.deleted = false and company.id = ?1")
    Optional<Company> findById(@Param("id") UUID id);

    @Query("SELECT company FROM Company company WHERE company.deleted = false AND company.cnpj = ?1")
    Optional<Company> findByCnpj(@Param("cnpj") String cnpj);

    @Modifying
    @Transactional
    @Query("UPDATE Company company SET company.deleted = true WHERE company.id = ?1")
    void delete(@Param("id") UUID uuid);
}
