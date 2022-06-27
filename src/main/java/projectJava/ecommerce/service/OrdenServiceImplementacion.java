package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImplementacion implements IOrdenService{

    @Autowired
    private IOrdenRepository  ordenRepository;
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
}
