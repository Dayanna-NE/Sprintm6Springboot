package cl.awakelab.sprint06.restcontroller;

import cl.awakelab.sprint06.entity.InstitucionPrevision;
import cl.awakelab.sprint06.entity.InstitucionSalud;
import cl.awakelab.sprint06.entity.Liquidacion;
import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.service.IInstitucionPrevisionService;
import cl.awakelab.sprint06.service.IInstitucionSaludService;
import cl.awakelab.sprint06.service.ILiquidacionService;
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
@RequestMapping("/api/liquidacion")
public class LiquidacionRestController {
    @Autowired
    ILiquidacionService objLiquidacionService;
    @Autowired
    IInstitucionSaludService objSaludService;
    @Autowired
    IInstitucionPrevisionService objPrevisionService;
    @Autowired
    ITrabajadorService objTrabajadorService;
    @GetMapping
    public List<Liquidacion> listarLiquidacion(){
        return objLiquidacionService.listarLiquidacion();
    }
    @GetMapping("/buscar/{idLiquidacion}")
    public ResponseEntity<?> buscarLiquidacionPorId(@PathVariable long idLiquidacion){
        try{
            Liquidacion liquidacion = objLiquidacionService.buscarLiquidacion(idLiquidacion);
            Map<String,Object> response = new HashMap<>();
            response.put("Liquidacion",liquidacion );
            response.put("mensaje","¡Usuario encontrado con exito! ٩(^ᴗ^)۶");
            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e){
            String msjError = "¡Liquidacion no pudo ser encontrado! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @PutMapping("/editar/{idLiquidacion}")
    public ResponseEntity<?> actualizarLiquidacionPorId(@PathVariable long idLiquidacion,@RequestBody Liquidacion liquidacionActualizar){
        try {
            Liquidacion liquidacionActual= objLiquidacionService.buscarLiquidacion(idLiquidacion);
            if(liquidacionActual!=null &&
                    liquidacionActualizar.getSueldoImponible() != null){
                liquidacionActualizar.setIdLiquidacion(idLiquidacion);
                liquidacionActualizar.setTrabajador(liquidacionActual.getTrabajador());
                liquidacionActualizar = objLiquidacionService.calcularLiquidacion(liquidacionActualizar,
                        liquidacionActual.getInstitucionPrevision(),
                        liquidacionActual.getInstitucionSalud());
                objLiquidacionService.actualizarLiquidacion(liquidacionActualizar);
                String mensaje = "¡Liquidacion actualizado con éxito! ٩(^ᴗ^)۶";
                //usando Map para enviar dos tipos de datos con clave diferentes
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", mensaje);
                response.put("liquidacion", liquidacionActual);
                return ResponseEntity.ok(response) ;
            }
            return ResponseEntity.ok("Falta ingresar el sueldo Imponible");

        }catch (NoSuchElementException e){
            String msjError = "¡El liquidacion con el id "+idLiquidacion+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }
    }
    @DeleteMapping("/eliminar/{idLiquidacion}")
    public ResponseEntity<?> eliminarLiquidacionPorId(@PathVariable long idLiquidacion){
        try{
            objLiquidacionService.buscarLiquidacion(idLiquidacion);
            objLiquidacionService.eliminarLiquidacion(idLiquidacion);
            return ResponseEntity.ok("Liquidacion eliminado con exito <(n.n')>");
        }catch (NoSuchElementException e){
            String msjError = "¡El liquidacion con el id "+idLiquidacion+" no existe! (＃￣0￣)";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearLiquidacion(@RequestBody Liquidacion liquidacionNuevo){
        try{
            if(liquidacionNuevo.getTrabajador().getIdTrabajador()!=null) {
                if (liquidacionNuevo.getSueldoImponible() != null) {
                    Trabajador trabajador = objTrabajadorService.buscarTrabajador(liquidacionNuevo.getTrabajador().getIdTrabajador());
                    InstitucionSalud institucionSalud = objSaludService.buscarInstitucionSalud(trabajador.getInstitucionSalud().getIdInstSalud());
                    InstitucionPrevision institucionPrevision = objPrevisionService.buscarInstitucionPrevision(trabajador.getInstitucionPrevision().getIdInstPrevision());
                    liquidacionNuevo = objLiquidacionService.calcularLiquidacion(liquidacionNuevo,institucionPrevision,institucionSalud);
                    Map<String, Object> response = new HashMap<>(); //guardamos un msj y el objeto
                    Liquidacion liquidacionCreado = objLiquidacionService.crearLiquidacion(liquidacionNuevo);
                    response.put("mensaje", "¡La liquidacion se ha registrado correctamente ~(*o*)~!");
                    response.put("liquidacion", liquidacionCreado);
                    return ResponseEntity.ok(response);
                }else{
                    return ResponseEntity.ok("Falta ingresar el sueldo Imponible");
                }
            }
            return ResponseEntity.ok("Falto Ingresar el trabajador");
        }catch (Exception e){
            String msjError = "¡El liquidacion no puede ser registrado, revise que los campos sean validos! (｀∇´)ψ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msjError);
        }
    }
}
