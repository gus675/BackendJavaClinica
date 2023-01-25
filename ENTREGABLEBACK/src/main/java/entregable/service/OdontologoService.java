package entregable.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entregable.entity.Odontologo;
import entregable.entity.OdontologoDTO;
import entregable.exception.ResourceNotFoundException;
import entregable.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService{
    @Autowired
    IOdontologoRepository odontologoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public OdontologoDTO crearOdontologo(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
        return mapper.convertValue(odontologo, OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO buscarOdontologoXId(Long id){
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        return odontologoDTO;
    }

    @Override
    public List<OdontologoDTO> buscarTodosLosOdontologos() {
        List <OdontologoDTO> todosLosOdontologosDTO= new ArrayList<>();
        for(Odontologo odontologo: odontologoRepository.findAll())
            todosLosOdontologosDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        return todosLosOdontologosDTO;
    }

    @Override
    public Optional<Odontologo> buscarOdontologoXMatricula(String matricula){
        return odontologoRepository.buscarOdontologoPorMatricula(matricula);
    }

    @Override
    public OdontologoDTO actualizarOdontologo(OdontologoDTO odontologo) throws ResourceNotFoundException{
        Odontologo actualizarOdontologo = mapper.convertValue(odontologo, Odontologo.class);
        if(actualizarOdontologo!=null){
            odontologoRepository.save(actualizarOdontologo);
            return odontologo;
        } else {
            throw new ResourceNotFoundException("NO SE ACTUALIZÓ INFORMACIÓN EN ODONTOLOGO");
        }

    }

    @Override
    public OdontologoDTO eliminarOdontologo(Long id) throws ResourceNotFoundException {
        OdontologoDTO borrarOdontologo= buscarOdontologoXId(id);
        if (borrarOdontologo!=null){
            odontologoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException(
                    "ERROR. NO SE ENCONTRÓ EL ODONTOLOGO ID: " + id);
        }
        return  mapper.convertValue(borrarOdontologo, OdontologoDTO.class);
    }

}

