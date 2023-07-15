package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Empleador;
import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("empleador")
public class EmpleadorController {
    @Autowired
    IEmpleadorService objEmpleadorService;
    @GetMapping
    public String listarEmpleadores(Model model){
        //obtenemos los elementos de mi BD y lo guardo en una list
        List<Empleador> listarEmpleadores = objEmpleadorService.listarEmpleador();
        model.addAttribute("title", "Lista de Empleadores");//actualizamos el title
        //Enviamos informacion al html a traves de nuestra variable html
        model.addAttribute("empleadoresHtml",listarEmpleadores);
        // Vista a la que nos dirigiremos
        return "listarEmpleador";
    }

    @GetMapping("/crear")
    public String crearEmpleador(Model model){
        model.addAttribute("title","Registro Empleador");
        return "registrarEmpleador";
    }

    @PostMapping("/crear")
    public String crearEmpleador(@ModelAttribute Empleador empleador){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        empleador.setUsuario(usuario);
        objEmpleadorService.cerarEmpleador(empleador);
        return "redirect:/empleador";
    }

    @GetMapping("/editar")
    public String editarEmpleador(Model model){
        //model.getAttribute();
        return "pam";
    }


    @PostMapping("/editar/{idEmpleador}")
    public String mostrarFormularioEditarUsuario(@PathVariable int idEmpleador, Model model){
        Empleador empleadorQueEditaremos = objEmpleadorService.buscarEmpleador(idEmpleador);
        model.addAttribute("title","Editar Trabajador");
        model.addAttribute("trabajadorHtml",empleadorQueEditaremos);
        return "editarTrabajador";
    }


}
