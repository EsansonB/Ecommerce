package projectJava.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.model.Usuario;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
