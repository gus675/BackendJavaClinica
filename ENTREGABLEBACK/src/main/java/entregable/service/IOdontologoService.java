package entregable.service;

import entregable.entity.Odontologo;
import entregable.exception.BadRequestException;
import entregable.entity.OdontologoDTO;
import entregable.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    public OdontologoDTO crearOdontologo(OdontologoDTO odontologoDTO);

    public OdontologoDTO eliminarOdontologo (Long id) throws BadRequestException, ResourceNotFoundException;

    public Optional<Odontologo> buscarOdontologoXMatricula(String matricula);

    public OdontologoDTO actualizarOdontologo(OdontologoDTO odontologo) throws ResourceNotFoundException;

    public OdontologoDTO buscarOdontologoXId(Long id);

    public List<OdontologoDTO> buscarTodosLosOdontologos();

}


