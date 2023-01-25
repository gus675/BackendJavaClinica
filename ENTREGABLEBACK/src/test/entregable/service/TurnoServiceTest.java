package entregable.service;

import entregable.entity.*;
import entregable.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void precargarDatos(){

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Anizacate");
        domicilio.setNumero(348);
        domicilio.setLocalidad("Cordoba");
        domicilio.setProvincia("Cordoba");
        PacienteDTO paciente= new PacienteDTO();
        paciente.setNombre("Gustavo");
        paciente.setApellido("Gonzalez");
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        OdontologoDTO odontologo= new OdontologoDTO();
        odontologo.setNombre("Colmillo");
        odontologo.setApellido("Blanco");
        odontologoService.crearOdontologo(odontologo);
    }

    @Test
    @Order(1)
    void guardarTurno() {
        precargarDatos();

        TurnoDTO turno= new TurnoDTO();
        turno.setFecha(LocalDate.of(2022,9,19));
        turno.setOdontologo_id(1L);
        turno.setPaciente_id(1L);
        TurnoDTO turnoGuardado=turnoService.guardarTurno(turno);
        assertEquals(1,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarTurnoXid() throws ResourceNotFoundException {
        TurnoDTO turnoBuscado=turnoService.buscarTurnoXId(1L);
        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    void getTodosLosTurnos() {
        List<TurnoDTO> getTurnosDTO= new ArrayList<>();
        getTurnosDTO = turnoService.getTodosLosTurnos();
        assertNotNull(getTurnosDTO);
    }

    @Test
    @Order(4)
    void actualizarTurno() {
        TurnoDTO turno= new TurnoDTO();
        turno.setId(1L);
        turno.setFecha(LocalDate.of(2022,10,26));
        turno.setOdontologo_id(1L);
        turno.setPaciente_id(1L);
        TurnoDTO turnoGuardado=turnoService.guardarTurno(turno);
        assertEquals(turno.getId(),turnoGuardado.getId());
    }

    @Test
    @Order(5)
    void eliminarTurno() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        TurnoDTO turnoBuscado = turnoService.buscarTurnoXId(1L);
        assertNull(turnoBuscado);
    }
}