package entregable.controller;

import entregable.entity.Paciente;
import entregable.entity.PacienteDTO;
import entregable.exception.ResourceNotFoundException;
import entregable.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        pacienteService.guardarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPacienteXId(id));
    }

    @GetMapping
    public Collection<PacienteDTO> getTodosPacientes(){
        return pacienteService.getTodos();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacienteXEmail(@PathVariable String email){
        Optional<Paciente> getPaciente=pacienteService.obtenerPacientePorEmail(email);
        if (getPaciente.isPresent()){
            return ResponseEntity.ok(getPaciente.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<PacienteDTO> actualizarPaciente(@RequestBody PacienteDTO paciente) throws ResourceNotFoundException {
        Optional<PacienteDTO> updatePaciente = Optional.ofNullable(pacienteService.buscarPacienteXId(paciente.getId()));
        if (updatePaciente.isPresent()) {
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("SE ELIMINO EL PACIENTE ID: "+id);
    }

}
