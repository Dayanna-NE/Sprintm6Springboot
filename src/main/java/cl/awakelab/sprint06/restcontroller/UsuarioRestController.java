package cl.awakelab.sprint06.restcontroller;

import cl.awakelab.sprint06.entity.Perfil;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
    @Autowired
    IUsuarioService objUsuarioService;

    //Listar Todos los Usuarios
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return objUsuarioService.listarUsuario();
    }
    //Buscar Usuario Por ID
    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<?>  buscarUsuarioPorId(@PathVariable int idUsuario){
        try{
            Usuario usuario = objUsuarioService.buscarUsuarioId(idUsuario);
            return ResponseEntity.ok(usuario);
        }catch (NoSuchElementException e){
            String msjError = "El usuario con el id "+idUsuario+" no ha podido ser encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    //Actualizar un usuario
    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<?> actualizarUsuarioPorId(@PathVariable int idUsuario,@RequestBody Usuario usuario){
        try {
            objUsuarioService.buscarUsuarioId(idUsuario);
            usuario.setIdUsuario(idUsuario);
            objUsuarioService.actualizarUsuario(usuario);
            String mensaje = "¡Usuario actualizado con éxito!";
            //usando Map para enviar dos tipos de datos con clave diferentes
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", mensaje);
            response.put("usuario", usuario);
            return ResponseEntity.ok(response) ;
        }catch (NoSuchElementException e){
            String msjError = "¡El usuario con el id "+idUsuario+" no existe! ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable int idUsuario){
        try{
            objUsuarioService.buscarUsuarioId(idUsuario);
            objUsuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.ok("Usuario eliminado con exito <(n.n')>");
        }catch (NoSuchElementException e){
            String msjError = "¡El usuario con el id "+idUsuario+" no existe! ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msjError);
        }

    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuarioNuevo){
        try{
            Map<String,Object> response = new HashMap<>(); //guardamos un msj y el objeto
            Usuario usuario = objUsuarioService.crearUsuario(usuarioNuevo);
            if (usuario == null){
                response.put("mensaje","¡El usuario se ha registrado correctamente ~(*o*)~!");
                response.put("usuario",usuarioNuevo);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok("¡El RUN "+ usuarioNuevo.getRun() +" ya se encuentra en uso! (｀∇´)ψ");

        }catch (Exception e){
            String msjError = "¡El usuario no puede ser registrado, revise que los campos sean validos! (｀∇´)ψ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msjError);
        }
    }

}
