package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Producto;
import projectJava.ecommerce.repository.ProductoRepository;

import java.util.Optional;

@Service
public class ProductoServiceImplementacion implements ProductoService{


    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);

    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);

    }
}
