package entregable.service;

import entregable.entity.Turno;
import entregable.entity.TurnoDTO;
import entregable.exception.BadRequestException;
import entregable.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {

    public TurnoDTO guardarTurno(TurnoDTO turno);

    public TurnoDTO buscarTurnoXId(Long id);

    Optional<Turno> buscarTurnoXfecha(LocalDate fecha);

    public void eliminarTurno (Long id) throws ResourceNotFoundException, ResourceNotFoundException;

    public TurnoDTO actualizarTurno(TurnoDTO turno);

    public List<TurnoDTO> getTodosLosTurnos();

}
