package cl.awakelab.sprint06.controller;

import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trabajador")
public class TrabajadorController {
    @Autowired
    ITrabajadorService objTrabajadorService;

    @GetMapping
    public String listarTrabajador(Model model){
        model.addAttribute("title", "Lista de Trabajadores");
        return "listarTrabajador";
    }

    @GetMapping("formularioCrear")
    public String formularioCrear(Model model){
        Trabajador trabajador = new Trabajador();
        model.addAttribute("title","Registrar Trabajador");
        model.addAttribute("trabajadorHtml",trabajador);
        return "registrarTrabajador";
    }

}
