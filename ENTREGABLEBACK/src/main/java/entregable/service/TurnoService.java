package entregable.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entregable.entity.Turno;
import entregable.entity.TurnoDTO;
import entregable.exception.ResourceNotFoundException;
import entregable.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService{

    @Autowired
    ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public TurnoDTO guardarTurno(TurnoDTO turno) {
        Turno newTurno = mapper.convertValue(turno, Turno.class);
        turnoRepository.save(newTurno);
        return mapper.convertValue(newTurno, TurnoDTO.class);
    }

    @Override
    public TurnoDTO buscarTurnoXId(Long id) {
        Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
        TurnoDTO dtoTurno = null;
        dtoTurno = mapper.convertValue(turnoEncontrado, TurnoDTO.class);
        return dtoTurno;
    }

    @Override
    public Optional<Turno> buscarTurnoXfecha(LocalDate fecha){
        return turnoRepository.buscarTurnoXfecha(fecha);
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        TurnoDTO elimTurno= buscarTurnoXId(id);
        if (elimTurno!=null){
            turnoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("ERROR. NO SE ELIMINÓ EL TURNO CON ID:" +id);
        }
    }

    @Override
    public TurnoDTO actualizarTurno(TurnoDTO turno) {
        Turno updateTurno = mapper.convertValue(turno, Turno.class);
        turnoRepository.save(updateTurno);
        return mapper.convertValue(updateTurno, TurnoDTO.class);
    }

    @Override
    public List<TurnoDTO> getTodosLosTurnos() {
        List <TurnoDTO> getTodoTurno= new ArrayList<>();
        for(Turno turno: turnoRepository.findAll())
            getTodoTurno.add(mapper.convertValue(turno, TurnoDTO.class));
        return getTodoTurno;
    }
}
