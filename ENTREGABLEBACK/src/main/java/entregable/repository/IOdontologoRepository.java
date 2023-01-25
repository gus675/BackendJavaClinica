package entregable.repository;

import entregable.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {
        @Query("select o from Odontologo o where o.matricula=?1")
        Optional<Odontologo> buscarOdontologoPorMatricula(String matricula);
}

