package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Liquidacion;
import cl.awakelab.sprint06.service.IInstitucionPrevisionService;
import cl.awakelab.sprint06.service.IInstitucionSaludService;
import cl.awakelab.sprint06.service.ILiquidacionService;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Liquidacion liquidacion = new Liquidacion();
        model.addAttribute("title","Registrar Liquidacion");
        model.addAttribute("liquidacionHtml",liquidacion);
        model.addAttribute("trabajadoresHtml",objTrabajadorService.listarTrabajador());
        model.addAttribute("listaSaludHtml",objInstitucionSaludService.listarInstitucionSalud());
        model.addAttribute("listaPrevisionHtml",objInstitucionPervisionService.listarInstitucionPrevision());
        return "registrarLiquidacion";
    }
    @PostMapping("/crear")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion){
        objLiquidacionService.cerarLiquidacion(liquidacion);
        return "Bienvenida";
    }
}
