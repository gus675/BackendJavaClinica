package entregable.service;

import entregable.entity.Paciente;
import entregable.entity.PacienteDTO;
import entregable.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPaciente() throws ResourceNotFoundException {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setApellido("Gonzalez");
        paciente.setNombre("Gustavo");
        paciente.setEmail("g@g.c");
        PacienteDTO pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarPacienteXId() throws ResourceNotFoundException {
        PacienteDTO pacienteBuscado=pacienteService.buscarPacienteXId(1L);
        assertNotNull(pacienteBuscado);
        assertEquals(1,pacienteBuscado.getId());
    }

    @Test
    @Order(3)
    void buscarPacienteXemail() {
        Optional<Paciente> pacienteBuscado=pacienteService.obtenerPacientePorEmail("g@g.c");
        Paciente pacienteBuscadoFinal=pacienteBuscado.get();
        assertNotNull(pacienteBuscadoFinal);
    }

    @Test
    @Order(4)
    void actualizarPaciente() {
        PacienteDTO paciente= new PacienteDTO();
        paciente.setApellido("Gonzalez");
        paciente.setNombre(("Roberto"));
        PacienteDTO pacienteGuardado=pacienteService.actualizarPaciente(paciente);
        assertEquals(paciente,pacienteGuardado);
    }

    @Test
    @Order(5)
    void buscarTodosLosPacientes() {
        List<PacienteDTO> getPacientesDTO= new ArrayList<>();
        getPacientesDTO = pacienteService.getTodos();
        assertNotNull(getPacientesDTO);
    }

    @Test
    @Order(6)
    void eliminarPaciente() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        PacienteDTO pacienteBuscado = pacienteService.buscarPacienteXId(1L);
        assertNull(pacienteBuscado);
    }
}