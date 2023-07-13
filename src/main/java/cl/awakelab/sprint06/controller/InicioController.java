package cl.awakelab.sprint06.controller;

import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Bienvenido");
        return "home";
    }
    @GetMapping("/2")
    public String home2(Model model){
        model.addAttribute("title", "Bienvenido");
        return "home2";
    }


}
