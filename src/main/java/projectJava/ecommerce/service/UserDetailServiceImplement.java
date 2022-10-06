//package projectJava.ecommerce.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//port org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//port org.springframework.stereotype.Service;
//port projectJava.ecommerce.model.Usuario;

import javax.servlet.http.HttpSession;
import java.util.Optional;

//@Service
//public class UserDetailServiceImplement implements UserDetailsService {

   // @Autowired
// private IUsuarioService usuarioService;

//@Autowired
//private BCryptPasswordEncoder bCrypt;

//@Autowired
// HttpSession session;

//private Logger log = LoggerFactory.getLogger(UserDetailServiceImplement.class);

    //valida el usuario atraves de esta clase y le deja ingresar
//@Override
// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//  log.info("esto es el user name");

//  Optional<Usuario> optionalUser= usuarioService.findByEmail(username);

// if(optionalUser.isPresent()){
//     log.info("esto es el id del usuario {}", optionalUser.get().getId());
//  session.setAttribute("idusuario", optionalUser.get().getId());
//   Usuario usuario= optionalUser.get();
//     return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
//  }else {
//     throw new UsernameNotFoundException("Usuario no encontrado");
//  }

// }
//}
