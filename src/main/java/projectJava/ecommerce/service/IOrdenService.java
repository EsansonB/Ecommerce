package projectJava.ecommerce.service;

import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.model.Usuario;

import java.util.List;
import java.util.Optional;


public interface IOrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save (Orden orden);
    String generarNumeroOrden();

    List<Orden> findByUsuario(Usuario usuario);
}
