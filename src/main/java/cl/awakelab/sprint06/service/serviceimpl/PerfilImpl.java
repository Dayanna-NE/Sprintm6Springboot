package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.Perfil;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.repository.IPerfilRepository;
import cl.awakelab.sprint06.service.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("perfilImpl")
public class PerfilImpl implements IPerfilService{
    @Autowired
    IPerfilRepository objPerfilRepo;
    @Override
    public Perfil cerarPerfil(Perfil perfil) {
        return objPerfilRepo.save(perfil);
    }

    @Override
    public List<Perfil> listarPerfil() {
        return objPerfilRepo.findAll();
    }

    @Override
    public Perfil buscarPerfil(int idPerfil) {
        return objPerfilRepo.findById(idPerfil).orElseThrow(()->new NoSuchElementException("No se encontr Perfil"));
    }

    @Override
    public Perfil actualizarPerfil(Perfil perfilActualizar) {
        Perfil perfil = buscarPerfil(perfilActualizar.getIdPerfil());
        return objPerfilRepo.save(perfilActualizar);
    }

    @Override
    public void eliminarPerfil(int idPerfil) {
        objPerfilRepo.deleteById(idPerfil);
    }
}
