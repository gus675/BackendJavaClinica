package entregable.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entregable.entity.Paciente;
import entregable.entity.PacienteDTO;
import entregable.exception.ResourceNotFoundException;
import entregable.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PacienteService implements IPacienteService {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    private IPacienteRepository pacienteRepository;


    @Override
    public PacienteDTO guardarPaciente(PacienteDTO paciente) {
        Paciente pacienteCreado = mapper.convertValue(paciente, Paciente.class);
        pacienteRepository.save(pacienteCreado);
        return mapper.convertValue(pacienteCreado, PacienteDTO.class);
    }

    @Override
    public PacienteDTO buscarPacienteXId(Long id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        return pacienteDTO;
    }

    @Override
    public Optional<Paciente> obtenerPacientePorEmail(String email){
            return pacienteRepository.buscarPacientePorEmail(email);
    }

    @Override
    public PacienteDTO actualizarPaciente(PacienteDTO paciente) {
        Paciente actualizarPaciente = mapper.convertValue(paciente, Paciente.class);
        pacienteRepository.save(actualizarPaciente);
        return paciente;
    }

    @Override
    public PacienteDTO eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<PacienteDTO> borrarPaciente= Optional.ofNullable(buscarPacienteXId(id));
        if (borrarPaciente.isPresent()){
        pacienteRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException(
                    "ERROR. NO SE ENCONTRO EL PACIENTE ID: " + id);
        }
        return  mapper.convertValue(borrarPaciente, PacienteDTO.class);
    }

    @Override
    public List<PacienteDTO> getTodos() {
        List <PacienteDTO> todosLosPacientesDTO= new ArrayList<>();
        for(Paciente paciente: pacienteRepository.findAll())
            todosLosPacientesDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        return todosLosPacientesDTO;
    }


}


