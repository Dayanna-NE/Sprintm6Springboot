package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bienvenida")
public class BienvenidaController {
    @GetMapping
    public String bienvenida(Model model){
        Usuario usuario=null;
        model.addAttribute("title","Bienvenido");
        model.addAttribute("usuarioHtml",usuario);
        return "Bienvenida";
    }
}
