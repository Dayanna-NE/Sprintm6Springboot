package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.repository.IUsuarioRepository;
import cl.awakelab.sprint06.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("usuarioImpl")
public class UsuarioImpl implements IUsuarioService {
    @Autowired
    IUsuarioRepository objUsuarioRepo;
    @Override
    public Usuario crearUsuario(Usuario usuario) {
        System.out.println("Desde el Usuario service1 "+usuario.getNombre() );
        Usuario usu = objUsuarioRepo.findByRun(usuario.getRun());

        if (usu == null) {
            objUsuarioRepo.save(usuario);
            return usu;
        }
        return usu;
    }

    @Override
    public List<Usuario> listarUsuario() {
        return objUsuarioRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioId(int idUsuario) {
        Usuario usu =  objUsuarioRepo.findById(idUsuario).orElseThrow(()->new NoSuchElementException("No usuario con el id "+idUsuario));
        return usu;

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuarioActualizar) {
        Usuario usuario= objUsuarioRepo.findById(usuarioActualizar.getIdUsuario()).orElseThrow(()->new NoSuchElementException("No usuario con el id "+ usuarioActualizar.getIdUsuario()));
        return objUsuarioRepo.save(usuarioActualizar);
    }

    @Override
    public void eliminarUsuario(int idUsuario) {
        objUsuarioRepo.deleteById(idUsuario);
    }
}
