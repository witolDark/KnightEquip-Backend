package witold.knightequipbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import witold.knightequipbackend.entities.Knight;

import java.util.Optional;

@Repository
public interface KnightRepository extends JpaRepository<Knight, Integer> {
    Optional<Knight> findFirstBy();
}
