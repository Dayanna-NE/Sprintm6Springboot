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
    public String loginVerificador(@ModelAttribute Usuario usuario){
        boolean loginExitoso = objLoginService.buscarUsuario(usuario.getRun(),usuario.getClave());
        if (loginExitoso){
            return "redirect:/bienvenida";
        }
        return "redirect:/login";
    }
}
