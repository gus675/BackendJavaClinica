package entregable.controller;

import entregable.entity.Odontologo;
import entregable.exception.BadRequestException;
import entregable.entity.OdontologoDTO;
import entregable.exception.ResourceNotFoundException;
import entregable.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {

    @Autowired
    IOdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<OdontologoDTO> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        return  ResponseEntity.ok(odontologoService.crearOdontologo(odontologoDTO));
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> buscarAllOdontologos (){
        return ResponseEntity.ok(odontologoService.buscarTodosLosOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarOdontologoXId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<OdontologoDTO> odontologoBuscado = Optional.ofNullable(odontologoService.buscarOdontologoXId(id));
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO PUDO ELIMINARSE EL ODONTOLOGO CON ID: "+id);
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarOdontologoXMatricula(@PathVariable String matricula){
        Optional<Odontologo> getOdontologo=odontologoService.buscarOdontologoXMatricula(matricula);
        if (getOdontologo.isPresent()){
            return ResponseEntity.ok(getOdontologo.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<OdontologoDTO> actualizarOdontologos(@RequestBody OdontologoDTO odontologo) throws ResourceNotFoundException {
        OdontologoDTO updateOdont = odontologoService.buscarOdontologoXId(odontologo.getId());
        if (updateOdont !=null) {
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id)
            throws ResourceNotFoundException, BadRequestException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("SE ELIMINO EL ODONTOLOGO ID: "+id);
    }

}












