package one.digitalinnovation.lingeriestock.repository;

import one.digitalinnovation.lingeriestock.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LingerieRepository extends JpaRepository<Piece, Long> {

    Optional<Piece> findByName(String name);
    Optional<Piece> findById(Long id);
}
