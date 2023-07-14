package cl.awakelab.sprint06.service.serviceimpl;

import ch.qos.logback.core.net.server.Client;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.repository.ILoginRepository;
import cl.awakelab.sprint06.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginImpl")
public class LoginImpl implements ILoginService {
    @Autowired
    ILoginRepository objLoginRepo;
    @Override
    public Usuario buscarUsuario(int rut, String clave) {
        Usuario usuario = objLoginRepo.findByRunAndClave(rut, clave);

        return usuario;
    }
}
