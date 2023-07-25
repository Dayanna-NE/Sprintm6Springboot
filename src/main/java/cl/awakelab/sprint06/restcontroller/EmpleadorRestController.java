package cl.awakelab.sprint06.restcontroller;

import cl.awakelab.sprint06.entity.Empleador;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/empleador")
public class EmpleadorRestController {
    @Autowired
    IEmpleadorService objEmpleadorService;
    @GetMapping
    public List<Empleador> listarEmpleador(){
       return objEmpleadorService.listarEmpleador();
    }
    @GetMapping("/buscar/{idEmpleador}")
    public ResponseEntity<?> buscarEmpleadorPorId(@PathVariable int idEmpleador){
        try{
            Empleador empleador = objEmpleadorService.buscarEmpleador(idEmpleador);
            Map<String,Object> response = new HashMap<>();
            response.put("Empleador",empleador );
            response.put("mensaje","¡Usuario encontrado con exito! ٩(^ᴗ^)۶");
            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e){
            String msjError = "¡Empleador no pudo ser encontrado! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @PutMapping("/editar/{idEmpleador}")
    public ResponseEntity<?> actualizarEmpleadorPorId(@PathVariable int idEmpleador,@RequestBody Empleador empleador){
        try {
            objEmpleadorService.buscarEmpleador(idEmpleador);
            empleador.setIdEmpleador(idEmpleador);
            objEmpleadorService.actualizarEmpleador(empleador);
            String mensaje = "¡Empleador actualizado con éxito! ٩(^ᴗ^)۶";
            //usando Map para enviar dos tipos de datos con clave diferentes
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", mensaje);
            response.put("empleador", empleador);
            return ResponseEntity.ok(response) ;
        }catch (NoSuchElementException e){
            String msjError = "¡El empleador con el id "+idEmpleador+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @DeleteMapping("/eliminar/{idEmpleador}")
    public ResponseEntity<?> eliminarEmpleadorPorId(@PathVariable int idEmpleador){
        try{
            objEmpleadorService.buscarEmpleador(idEmpleador);
            objEmpleadorService.eliminarEmpleador(idEmpleador);
            return ResponseEntity.ok("Empleador eliminado con exito <(n.n')>");
        }catch (NoSuchElementException e){
            String msjError = "¡El empleador con el id "+idEmpleador+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleador(@RequestBody Empleador empleadorNuevo){
        try{
            Map<String,Object> response = new HashMap<>(); //guardamos un msj y el objeto

            if (!objEmpleadorService.buscarEmpleadorRun(empleadorNuevo.getRun())){
                Empleador empleadorCreado = objEmpleadorService.crearEmpleador(empleadorNuevo);
                response.put("mensaje","¡El empleador se ha registrado correctamente ~(*o*)~!");
                response.put("empleador",empleadorCreado);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok("¡El RUN "+ empleadorNuevo.getRun() +" ya se encuentra en uso! (｀∇´)ψ");

        }catch (Exception e){
            String msjError = "¡El empleador no puede ser registrado, revise que los campos sean validos! (｀∇´)ψ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msjError);
        }
    }
}
