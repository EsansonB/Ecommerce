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
import projectJava.ecommerce.model.Usuario;
import projectJava.ecommerce.service.IDetalleOrdenService;
import projectJava.ecommerce.service.IOrdenService;
import projectJava.ecommerce.service.IProductoService;
import projectJava.ecommerce.service.IUsuarioService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService IProductoService;
    @Autowired
    private IUsuarioService IUsuarioService;
    @Autowired
    private IOrdenService ordenService;
    @Autowired
    private IDetalleOrdenService detalleOrdenService;


    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();
    
    //datos de la orden
    Orden orden= new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        model.addAttribute("productos", IProductoService.findAll());


        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = IProductoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden= new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal=0;
        
        Optional<Producto> optionalProducto = IProductoService.get(id);
        log.info("producto añadido: {} ", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto=optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //validar para que el producto no se agregue más de una vez
        Integer idPorducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idPorducto);

        if(!ingresado){
            detalles.add(detalleOrden);
        }



        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    //quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart (@PathVariable Integer id, Model model) {

        //lista de nuevos productos
        List<DetalleOrden> ordenesNueva= new ArrayList<DetalleOrden>();

        for(DetalleOrden detalleOrden: detalles) {
            if(detalleOrden.getProducto().getId()!=id){
                ordenesNueva.add(detalleOrden);
            }
        }

        //poner la nueva lista con los productos restantes
        detalles=ordenesNueva;

        double sumaTotal=0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";

    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "/usuario/carrito";

    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Usuario usuario= IUsuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "usuario/resumenorden";

    }
    //guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //Usuario
        Usuario usuario= IUsuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for (DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);

        }

        //limpiar lista
        orden = new Orden();
        detalles.clear();

        return "redirect:/";

    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String busqueda, Model model){
        log.info("Busqueda de producto:  ()", busqueda);
        List<Producto> productos= IProductoService.findAll().stream().filter(p -> p.getNombre().contains(busqueda)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";

    }

}
