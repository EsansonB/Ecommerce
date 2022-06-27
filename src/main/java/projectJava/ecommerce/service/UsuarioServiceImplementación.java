package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Usuario;
import projectJava.ecommerce.repository.IUsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioServiceImplementación implements IUsuarioService {

    @Autowired
    private IUsuarioRepository IUsuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return IUsuarioRepository.findById(id);
    }
}
