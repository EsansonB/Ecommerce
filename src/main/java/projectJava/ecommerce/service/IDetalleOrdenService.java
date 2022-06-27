package projectJava.ecommerce.service;

import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.DetalleOrden;

@Service
public interface IDetalleOrdenService {
    DetalleOrden save (DetalleOrden detalleOrden);
}
