package entregable.repository;

import entregable.entity.Turno;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno,Long> {

    @Query("select t from Turno t where t.fecha=?1")
    Optional<Turno> buscarTurnoXfecha(LocalDate fecha);
}