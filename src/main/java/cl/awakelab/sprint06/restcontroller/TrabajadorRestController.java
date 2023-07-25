package cl.awakelab.sprint06.restcontroller;

import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/trabajador")
public class TrabajadorRestController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @GetMapping
    public List<?> listarTrabajador(){
        return objTrabajadorService.listarTrabajador();
    }
    @GetMapping("/buscar/{idTrabajador}")
    public ResponseEntity<?> buscarTrabajadorPorId(@PathVariable int idTrabajador){
        try{
            Trabajador trabajador = objTrabajadorService.buscarTrabajador(idTrabajador);
            Map<String,Object> response = new HashMap<>();
            response.put("Trabajador",trabajador );
            response.put("mensaje","¡Usuario encontrado con exito! ٩(^ᴗ^)۶");
            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e){
            String msjError = "¡Trabajador no pudo ser encontrado! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @PutMapping("/editar/{idTrabajador}")
    public ResponseEntity<?> actualizarTrabajadorPorId(@PathVariable int idTrabajador,@RequestBody Trabajador trabajador){
        try {
            objTrabajadorService.buscarTrabajador(idTrabajador);
            trabajador.setIdTrabajador(idTrabajador);
            objTrabajadorService.actualizarTrabajador(trabajador);
            String mensaje = "¡Trabajador actualizado con éxito! ٩(^ᴗ^)۶";
            //usando Map para enviar dos tipos de datos con clave diferentes
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", mensaje);
            response.put("trabajador", trabajador);
            return ResponseEntity.ok(response) ;
        }catch (NoSuchElementException e){
            String msjError = "¡El trabajador con el id "+idTrabajador+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @DeleteMapping("/eliminar/{idTrabajador}")
    public ResponseEntity<?> eliminarTrabajadorPorId(@PathVariable int idTrabajador){
        try{
            objTrabajadorService.buscarTrabajador(idTrabajador);
            objTrabajadorService.eliminarTrabajador(idTrabajador);
            return ResponseEntity.ok("Trabajador eliminado con exito <(n.n')>");
        }catch (NoSuchElementException e){
            String msjError = "¡El trabajador con el id "+idTrabajador+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearTrabajador(@RequestBody Trabajador trabajadorNuevo){
        try{
            Map<String,Object> response = new HashMap<>(); //guardamos un msj y el objeto

            if (!objTrabajadorService.buscarTrabajadorRun(trabajadorNuevo.getRun())){
                Trabajador trabajadorCreado = objTrabajadorService.crearTrabajador(trabajadorNuevo);
                response.put("mensaje","¡El trabajador se ha registrado correctamente ~(*o*)~!");
                response.put("trabajador",trabajadorCreado);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok("¡El RUN "+ trabajadorNuevo.getRun() +" ya se encuentra en uso! (｀∇´)ψ");

        }catch (Exception e){
            String msjError = "¡El trabajador no puede ser registrado, revise que los campos sean validos! (｀∇´)ψ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msjError);
        }
    }

}
