package projectJava.ecommerce.service;

import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Orden;

import java.util.List;

@Service
public interface IOrdenService {
    List<Orden> findAll();
    Orden save (Orden orden);
}
