package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.InstitucionPrevision;
import cl.awakelab.sprint06.entity.InstitucionSalud;
import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.service.IInstitucionPrevisionService;
import cl.awakelab.sprint06.service.IInstitucionSaludService;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("trabajador")
public class TrabajadorController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @Autowired
    IInstitucionSaludService objSaludService;
    @Autowired
    IInstitucionPrevisionService objPrevisionService;
      @GetMapping
    public String listarTrabajador(Model model){
        List<Trabajador> trabajadores  = objTrabajadorService.listarTrabajador();
        model.addAttribute("title", "Lista de Trabajadores");
        model.addAttribute("trabajadoresHtml",trabajadores);
        return "listarTrabajador";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model){
        List<InstitucionSalud> institucionSaludList = objSaludService.listarInstitucionSalud();
        List<InstitucionPrevision> institucionPrevisionList = objPrevisionService.listarInstitucionPrevision();
        Trabajador trabajador = new Trabajador();
        model.addAttribute("title","Registrar Trabajador");
        model.addAttribute("trabajadorHtml",trabajador);
        model.addAttribute("listaSaludHtml",institucionSaludList);
        model.addAttribute("ListaPrevisionHtml",institucionPrevisionList);
        return "registrarTrabajador";
    }
    @PostMapping("/crear")
    public String crearTrabajador(@ModelAttribute Trabajador trabajador, Model model, RedirectAttributes redirectAttributes){

        if (objTrabajadorService.buscarTrabajadorRun(trabajador.getRun())){//si se repite el run
            List<InstitucionSalud> institucionSaludList = objSaludService.listarInstitucionSalud();
            List<InstitucionPrevision> institucionPrevisionList = objPrevisionService.listarInstitucionPrevision();
            model.addAttribute("trabajadorHtml",trabajador);
            model.addAttribute("listaSaludHtml",institucionSaludList);
            model.addAttribute("ListaPrevisionHtml",institucionPrevisionList);
            model.addAttribute("title","Registrar Trabajador");
            return "registrarTrabajador";
        }//si no se repite el run
        objTrabajadorService.crearTrabajador(trabajador);
        redirectAttributes.addFlashAttribute("mensaje","El trabajador "+trabajador.getNombre()+" registrado correctamente ~(*o*)~");
        return "redirect:/trabajador";
    }

    @GetMapping("/editar/{idTrabajador}")
    public String editarFormulario(@PathVariable int idTrabajador, Model model){
        List<InstitucionSalud> institucionSaludList = objSaludService.listarInstitucionSalud();
        List<InstitucionPrevision> institucionPrevisionList = objPrevisionService.listarInstitucionPrevision();
        Trabajador trabajador = objTrabajadorService.buscarTrabajador(idTrabajador);
        System.out.println("<<<<<-<-<-<-<-<-<_>_>_>_>_>_>_>_>_>_>"+trabajador.getIdTrabajador());
        model.addAttribute("title","Editar Trabajador");
        model.addAttribute("trabajadorHtml",trabajador);
        model.addAttribute("listaSaludHtml",institucionSaludList);
        model.addAttribute("ListaPrevisionHtml",institucionPrevisionList);
        return "editarTrabajador";
    }
    @PostMapping("/editar")
    public String editarTrabajador(@ModelAttribute Trabajador trabajador,RedirectAttributes redirectAttributes){
          objTrabajadorService.actualizarTrabajador(trabajador);
          redirectAttributes.addFlashAttribute("mensaje","El trabajador "+
                  trabajador.getNombre()+" "+trabajador.getApellido1()+" "+trabajador.getApellido2()+
                  " ha sido actualizado con exito <(n.n)>");
          return "redirect:/trabajador";
    }

    @GetMapping("/eliminar/{idTrabajador}")
    public String eliminarTrabajador(@PathVariable int idTrabajador,RedirectAttributes redirectAttributes){
          objTrabajadorService.eliminarTrabajador(idTrabajador);
          redirectAttributes.addFlashAttribute("mensaje","Trabajador eliminado con exito <(^.^')>");
          return "redirect:/trabajador";
    }

}
