package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Liquidacion;
import cl.awakelab.sprint06.service.ILiquidacionService;
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

    @GetMapping
    public String listarLiquidacion(Model model){
        List<Liquidacion> liquidaciones = objLiquidacionService.listarLiquidacion();
        model.addAttribute("title","Lista de Liquidaciones");
        model.addAttribute("liquidacionesHtml",liquidaciones);
        return "listarLiquidaciones";
    }

    @GetMapping("/formularioCrear")
    public String formularioCrear(Model model){
        Liquidacion liquidacion = new Liquidacion();
        model.addAttribute("title","Registrar Liquidacion");
        model.addAttribute("liquidacionHtml",liquidacion);
        return "registroLiquidacion";
    }
    @PostMapping("/crear")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion){
        objLiquidacionService.cerarLiquidacion(liquidacion);
        return "Bienvenida";
    }
}
