package ai.semfila.api.repository;


import ai.semfila.api.model.User;
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
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT user FROM User user WHERE user.deleted = false") @Override
    Page<User> findAll(Pageable pageable);

    @Query("SELECT user FROM User user WHERE user.deleted = false and user.id = ?1")
    Optional<User> findById(@Param("uuid") UUID uuid);

    @Query("SELECT user FROM User user WHERE user.deleted = false and user.cpf = ?1")
    Optional<User> findByCpf(@Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.deleted = true WHERE user.id = ?1")
    void delete(@Param("id") UUID uuid);

}
