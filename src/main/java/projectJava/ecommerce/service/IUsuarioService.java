package projectJava.ecommerce.service;

import projectJava.ecommerce.model.Usuario;

import java.util.Optional;


public interface IUsuarioService {
    Optional<Usuario> findById(Integer id);
}
