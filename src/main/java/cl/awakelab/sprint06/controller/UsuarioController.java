package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    IUsuarioService objUsuarioService;
    @GetMapping()
    public String listarUsuario(Model model){
        List<Usuario> usuarios = objUsuarioService.listarUsuario();
        model.addAttribute("title","Lista de Usuarios");
        model.addAttribute("usuariosHtml",usuarios);
        return "listarUsuario";
    }
}
