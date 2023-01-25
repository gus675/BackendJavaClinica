package entregable.repository;

import entregable.entity.Odontologo;
import entregable.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.email=?1")
    Optional<Paciente> buscarPacientePorEmail(String email);
}
