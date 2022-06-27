package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Usuario;
import projectJava.ecommerce.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioServiceImplementación implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}
