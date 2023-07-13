package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario){
        objUsuarioService.crearUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/editar/{idUsuario}")
    public String mostrarFormularioEditarUsuario(@PathVariable int idUsuario, Model model){
        Usuario usuarioQueEditaremos = objUsuarioService.buscarUsuarioId(idUsuario);
        model.addAttribute("title","Editar Usuario");
        model.addAttribute("usuarioHtml",usuarioQueEditaremos);
        return "editarUsuario";
    }
    @PostMapping("/editar")
    public String actualizarUsuario(@ModelAttribute Usuario usuarioActualizar){
        objUsuarioService.actualizarUsuario(usuarioActualizar);
        return "redirect:/usuario";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable int idUsuario){
        objUsuarioService.eliminarUsuario(idUsuario);
        return "redirect:/usuario";
    }


}
