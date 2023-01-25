package entregable.service;

import entregable.entity.Odontologo;
import entregable.entity.OdontologoDTO;
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
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setApellido("Diente");
        odontologo.setMatricula(("MP675"));
        OdontologoDTO odontologoGuardado = odontologoService.crearOdontologo(odontologo);
        assertEquals(1,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologoXId() throws ResourceNotFoundException {
        OdontologoDTO odontologoBuscado=odontologoService.buscarOdontologoXId(1L);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    void buscarOdontologoXMatricula() {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXMatricula("MP675");
        Odontologo odontologoBuscadoFinal=odontologoBuscado.get();
        assertNotNull(odontologoBuscadoFinal);
    }

    @Test
    @Order(4)
    void actualizarOdontologo() throws ResourceNotFoundException {
        OdontologoDTO odontologo= new OdontologoDTO();
        odontologo.setApellido("Muela");
        odontologo.setMatricula(("MP675"));
        OdontologoDTO odontologoGuardado=odontologoService.actualizarOdontologo(odontologo);
        assertEquals(odontologo,odontologoGuardado);
    }

    @Test
    @Order(5)
    void buscarTodosLosOdontologos() {
        List<OdontologoDTO> todosLosOdontologosDTO= new ArrayList<>();
        todosLosOdontologosDTO=odontologoService.buscarTodosLosOdontologos();
        assertNotNull(todosLosOdontologosDTO);
    }

    @Test
    @Order(6)
    void eliminarOdontologo() throws ResourceNotFoundException{
        odontologoService.eliminarOdontologo(1L);
        OdontologoDTO odontologoBuscado=odontologoService.buscarOdontologoXId(1L);
        assertNull(odontologoBuscado);
    }
}
