package entregable.service;

import entregable.entity.Paciente;
import entregable.entity.PacienteDTO;
import entregable.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    PacienteDTO buscarPacienteXId(Long id);
    List<PacienteDTO> getTodos();
    PacienteDTO guardarPaciente(PacienteDTO paciente) throws ResourceNotFoundException;
    Optional<Paciente> obtenerPacientePorEmail(String email);
    public PacienteDTO actualizarPaciente(PacienteDTO paciente);
    PacienteDTO eliminarPaciente(Long id) throws ResourceNotFoundException;

}
