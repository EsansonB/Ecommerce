package projectJava.ecommerce.controller;


import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.model.Producto;
import projectJava.ecommerce.service.IOrdenService;
import projectJava.ecommerce.service.IProductoService;
import projectJava.ecommerce.service.IUsuarioService;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IProductoService IProductoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    //para poder imprimir los valores
    private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model){

        List<Producto> productos= IProductoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public  String usuarios (Model model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public  String ordenes (Model model){
        model.addAttribute("ordenes",ordenService.findAll());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public  String detalle (Model model, @PathVariable Integer id) {
        logg.info("id de la orden {}", id );
        Orden orden = ordenService.findById(id).get();


        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }



}
