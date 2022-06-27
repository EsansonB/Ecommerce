package projectJava.ecommerce.service;

import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Orden;

@Service
public interface IOrdenService {
    Orden save (Orden orden);
}
