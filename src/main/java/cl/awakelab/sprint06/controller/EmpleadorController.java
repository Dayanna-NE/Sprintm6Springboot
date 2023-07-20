package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Empleador;
import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String mostrarFormularioCrearEmpleador(Model model){
        Empleador empleador= new Empleador();
        model.addAttribute("title","Registro Empleador");
        model.addAttribute("empleadorHtml",empleador);
        return "registrarEmpleador";
    }

    @PostMapping("/crear")
    public String crearEmpleador(@ModelAttribute Empleador empleador, Model model,RedirectAttributes redirectAttributes){
        //para agregar el id_usuario de mi FK
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        empleador.setUsuario(usuario);

        //validamos RUN
        if (objEmpleadorService.buscarEmpleadorRun(empleador.getRun())){ //Si existe
            model.addAttribute("title","Registro Usuario");
            model.addAttribute("empleadorHtml",empleador);
            return "registrarEmpleador";
        }
        //si no existe el run
        objEmpleadorService.cerarEmpleador(empleador); //Creado correctamente
        redirectAttributes.addFlashAttribute("mensaje","El empleador "+empleador.getNombre()+" registrado correctamente ~(*o*)~");
        return "redirect:/empleador";

    }

    @GetMapping("/editar/{idEmpleador}")
    public String mostrarFormularioEditarEmpleador(Model model,@PathVariable int idEmpleador){
        Empleador empleador = objEmpleadorService.buscarEmpleador(idEmpleador);
        model.addAttribute("title","Editar Empleador");
        model.addAttribute("empleadorHtml",empleador);
        return "editarEmpleador";
    }


    @PostMapping("/editar")
    public String editarEmpleador(@ModelAttribute Empleador empleador,RedirectAttributes redirectAttributes){
        objEmpleadorService.actualizarEmpleador(empleador);
        redirectAttributes.addFlashAttribute("mensaje","El empleador "+
                empleador.getNombre()+" "+empleador.getApellido1()+" "+empleador.getApellido2()+
                " ha sido actualizado con exito <(n.n)>");
        return "redirect:/empleador";
    }
    @GetMapping("/eliminar/{idEmpleador}")
    public String eliminarEmpleador(@PathVariable int idEmpleador, RedirectAttributes redirectAttributes){
        objEmpleadorService.eliminarEmpleador(idEmpleador);
        redirectAttributes.addFlashAttribute("mensaje","Empleador eliminado con exito <(^.^')>");

        return "redirect:/empleador";
    }


}
