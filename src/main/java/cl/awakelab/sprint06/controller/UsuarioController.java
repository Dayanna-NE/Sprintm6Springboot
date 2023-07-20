package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
    @GetMapping("/crear/{base}")
    public String enviarCrear(Model model,@PathVariable int base){
        Usuario usuarioCrear = new Usuario();
        model.addAttribute(base);
        model.addAttribute("usuarioHtml",usuarioCrear);
        model.addAttribute("title", "Registro de Usuario");
        return "registro";
    }

    @PostMapping("/crear/{base}")
    public String crearUsuario(@ModelAttribute Usuario usuario, @PathVariable int base, Model model, RedirectAttributes redirectAttributes){
        Usuario usuarioVerificarRut = objUsuarioService.crearUsuario(usuario);
        if (usuarioVerificarRut == null){
            if (base==2) {
                redirectAttributes.addFlashAttribute("mensaje","El usuario "+usuario.getNombre()+" registrado correctamente ~(*o*)~");
                return "redirect:/usuario";
            }else{
                redirectAttributes.addFlashAttribute("mensaje","El usuario "+usuario.getNombre()+" registrado correctamente ~(*o*)~");
                return "redirect:/";
            }
        }
            model.addAttribute("title", "Registro de Usuario");
            model.addAttribute("usuarioHtml",usuario);
            model.addAttribute("base",base);
            return "registro";
    }


    @GetMapping("/editar/{idUsuario}")
    public String mostrarFormularioEditarUsuario(@PathVariable int idUsuario, Model model){
        Usuario usuarioQueEditaremos = objUsuarioService.buscarUsuarioId(idUsuario);
        model.addAttribute("title","Editar Usuario");
        model.addAttribute("usuarioHtml",usuarioQueEditaremos);
        return "editarUsuario";
    }
    @PostMapping("/editar")
    public String actualizarUsuario(@ModelAttribute Usuario usuarioActualizar,RedirectAttributes redirectAttributes){
        objUsuarioService.actualizarUsuario(usuarioActualizar);
        redirectAttributes.addFlashAttribute("mensaje","El usuario "+
                usuarioActualizar.getNombre()+" "+usuarioActualizar.getApellido1()+" "+usuarioActualizar.getApellido2()+
                " ha sido actualizado con exito <(n.n)>");
        return "redirect:/usuario";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable int idUsuario, RedirectAttributes redirectAttributes){
        objUsuarioService.eliminarUsuario(idUsuario);
        redirectAttributes.addFlashAttribute("mensaje","Usuario eliminado con exito <(^.^')>");
        return "redirect:/usuario";
    }


}
