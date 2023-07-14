package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    ILoginService objLoginService;

    @GetMapping
    public String login(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("title", "Iniciar Sesión");
        model.addAttribute("usuarioHtml",usuario);
        return "login";
    }
    @PostMapping
    public String loginVerificador(@ModelAttribute Usuario usuario, Model model){
        if (usuario.getRun()!= null && !usuario.getClave().isEmpty()){

            Usuario usuarioLogin = objLoginService.buscarUsuario(usuario.getRun(),usuario.getClave());
            if (usuarioLogin != null){

                    model.addAttribute("mensaje","Usuario "+usuarioLogin.getNombre()+" ingreso correctamente ~(*o*)~");
                    model.addAttribute("nombreUsuario", usuarioLogin.getNombre());
                    return "bienvenida";

            }
        }
        model.addAttribute("mensaje","EL USUARIO:"+usuario.getRun()+" O CONTRASEÑA: "+usuario.getClave()+" QUE HA INGRESADO NO EXISTE <(-.-)>");
        model.addAttribute("usuarioHtml",usuario);
        return "login";
    }
}
