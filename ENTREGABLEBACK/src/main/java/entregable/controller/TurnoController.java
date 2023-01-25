
package entregable.controller;

import entregable.entity.Turno;
import entregable.entity.TurnoDTO;
import entregable.exception.BadRequestException;
import entregable.exception.ResourceNotFoundException;
import entregable.service.IOdontologoService;
import entregable.service.IPacienteService;
import entregable.service.ITurnoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")

public class TurnoController {

    @Autowired
    ITurnoService turnoService;
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IOdontologoService odontologoService;

    public TurnoController(ITurnoService turnoService, IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        ResponseEntity<TurnoDTO> respuesta;
        if (odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null
                && pacienteService.buscarPacienteXId(turno.getPaciente().getId())!=null){
            respuesta =   ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnosXId(@PathVariable Long id){
        Optional<TurnoDTO> getTurno = Optional.ofNullable(turnoService.buscarTurnoXId(id));
        if (getTurno.isPresent()) {
            return ResponseEntity.ok(getTurno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<TurnoDTO> actualizarTurnos(@RequestBody TurnoDTO turno) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<TurnoDTO> respuesta;
        Optional<TurnoDTO> updateTurno = Optional.ofNullable(turnoService.buscarTurnoXId(turno.getId()));
        if (updateTurno.isPresent()) {
            if (odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null
                    &&pacienteService.buscarPacienteXId(turno.getPaciente().getId())!=null){
                return ResponseEntity.ok(turnoService.actualizarTurno(turno));
            } else {
                respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodosLosTurnos (){
        return ResponseEntity.ok(turnoService.getTodosLosTurnos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("SE ELIMINO EL TURNO ID: "+id);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<Turno> buscarTurnoXFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                       LocalDate fecha){
        Optional<Turno> getTurno=turnoService.buscarTurnoXfecha(fecha);
        if (getTurno.isPresent()){
            return ResponseEntity.ok(getTurno.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}