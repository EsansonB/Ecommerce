package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.DetalleOrden;
import projectJava.ecommerce.repository.IDetalleOrdenRepository;

@Service
public class DetalleOderdenService implements IDetalleOrdenService {

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
