package entregable.controller;

import entregable.entity.*;
import entregable.exception.ResourceNotFoundException;
import entregable.service.OdontologoService;
import entregable.service.PacienteService;
import entregable.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoControllerTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarInfo() throws ResourceNotFoundException {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle x");
        domicilio.setNumero(584);
        domicilio.setLocalidad("Salta");
        domicilio.setProvincia("Salta");
        PacienteDTO paciente= new PacienteDTO();
        paciente.setNombre("Gustavo");
        paciente.setApellido("Gonzalez");
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        OdontologoDTO odontologo= new OdontologoDTO();
        odontologo.setNombre("Colmillo");
        odontologo.setApellido("Cariado");
        odontologoService.crearOdontologo(odontologo);

        TurnoDTO turno= new TurnoDTO();
        turno.setFecha(LocalDate.of(2022,10,26));
        turno.setOdontologo_id(1L);
        turno.setPaciente_id(1L);
        TurnoDTO turnoGuardado=turnoService.guardarTurno(turno);
    }
    @Test
    public void listarTurnosTest() throws Exception {
        this.cargarInfo();
        MvcResult respuesta=mockMvc.perform(MockMvcRequestBuilders.
                        get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}