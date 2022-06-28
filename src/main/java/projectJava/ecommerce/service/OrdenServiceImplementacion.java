package projectJava.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJava.ecommerce.model.Orden;
import projectJava.ecommerce.repository.IOrdenRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImplementacion implements IOrdenService{

    @Autowired
    private IOrdenRepository  ordenRepository;


    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }




    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }


    public  String generarNumeroOrden() {
        Integer num=0;
        String numeroOrden=String.valueOf(num);
        int numDigitos=numeroOrden.length();//numero de digitos

        for(int j=numDigitos; j<=9;j++)
            numeroOrden="0"+numeroOrden; //añadimos los ceros al numerode orden ´numero de orden



        return numeroOrden;
    }


}
