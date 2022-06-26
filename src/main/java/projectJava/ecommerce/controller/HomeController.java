package projectJava.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectJava.ecommerce.model.DetalleOrden;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.model.Producto;
import projectJava.ecommerce.service.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();
    
    //datos de la orden
    Orden orden= new Orden();

    @GetMapping("")
    public String home(Model model) {

        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addcart(@RequestParam Integer id, @RequestParam Integer cantidad) {
        DetalleOrden detalleOrden= new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal=0;
        
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("producto añadido {} ", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        
        return "usuario/carrito";
    }

}