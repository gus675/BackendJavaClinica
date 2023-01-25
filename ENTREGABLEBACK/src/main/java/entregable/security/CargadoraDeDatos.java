package entregable.security;

import entregable.entity.RolUsuario;
import entregable.entity.Usuario;
import entregable.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "user";
        String passHash = passwordEncoder.encode(pass);
        Usuario usuario = new Usuario();
        usuario.setEmail("user@");
        usuario.setPassword(passHash);
        usuario.setUsuarioRole(RolUsuario.ROLE_USER);
        usuarioRepository.save(usuario);

        String pass2="admin";
        String passHash2=passwordEncoder.encode(pass2);
        Usuario usuario2= new Usuario();
        usuario2.setEmail("admin@");
        usuario2.setPassword(passHash2);
        usuario2.setUsuarioRole(RolUsuario.ROLE_ADMIN);
        usuarioRepository.save(usuario2);

    }
}
