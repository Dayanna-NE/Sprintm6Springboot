package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Liquidacion;
import cl.awakelab.sprint06.service.IInstitucionPrevisionService;
import cl.awakelab.sprint06.service.IInstitucionSaludService;
import cl.awakelab.sprint06.service.ILiquidacionService;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("liquidacion")
public class LiquidacionController {
    @Autowired
    ILiquidacionService objLiquidacionService;
    @Autowired
    ITrabajadorService objTrabajadorService;
    @Autowired
    IInstitucionSaludService objInstitucionSaludService;
    @Autowired
    IInstitucionPrevisionService objInstitucionPervisionService;
    @GetMapping
    public String listarLiquidacion(Model model){
        List<Liquidacion> liquidaciones = objLiquidacionService.listarLiquidacion();

        model.addAttribute("title","Lista de Liquidaciones");
        model.addAttribute("liquidacionesHtml",liquidaciones);
        return "listarLiquidacion";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model){
        model.addAttribute("title","Registrar Liquidacion");
        model.addAttribute("liquidacionHtml",new Liquidacion());
        model.addAttribute("trabajadoresHtml",objTrabajadorService.listarTrabajador());
        model.addAttribute("listaSaludHtml",objInstitucionSaludService.listarInstitucionSalud());
        model.addAttribute("listaPrevisionHtml",objInstitucionPervisionService.listarInstitucionPrevision());
        return "registrarLiquidacion";
    }
    @PostMapping("/crear")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion, RedirectAttributes redirectAttributes){
        objLiquidacionService.crearLiquidacion(liquidacion);
        redirectAttributes.addFlashAttribute("mensaje","El número de liquidación: "+liquidacion.getIdLiquidacion() +", se ha registrado correctamente ~(*o*)~");
        return "redirect:/liquidacion";
    }
    @GetMapping("/editar/{idLiquidacion}")
    public String editarFormulario(@PathVariable long idLiquidacion, Model model){
        Liquidacion liquidacion = objLiquidacionService.buscarLiquidacion(idLiquidacion);
        model.addAttribute("title","Editar Liquidacion");
        model.addAttribute("liquidacionHtml",liquidacion);
        model.addAttribute("trabajadoresHtml",objTrabajadorService.listarTrabajador());
        model.addAttribute("listaSaludHtml",objInstitucionSaludService.listarInstitucionSalud());
        model.addAttribute("listaPrevisionHtml",objInstitucionPervisionService.listarInstitucionPrevision());
        return "editarLiquidacion";
    }
    @PostMapping("/editar")
    public String editarLiquidacion(@ModelAttribute Liquidacion liquidacion, RedirectAttributes redirectAttributes){
        objLiquidacionService.actualizarLiquidacion(liquidacion);
        redirectAttributes.addFlashAttribute("mensaje","La liquidación número: "+liquidacion.getIdLiquidacion()+" ha sido actualizada con exito <(n.n)>");
        return "redirect:/liquidacion";
    }
    @GetMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacion(@PathVariable long idLiquidacion, RedirectAttributes redirectAttributes){
        objLiquidacionService.eliminarLiquidacion(idLiquidacion);
        redirectAttributes.addFlashAttribute("mensaje","Liquidación eliminada con exito <(^.^')>");
        return "redirect:/liquidacion";
    }
}
