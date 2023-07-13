package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Usuario;

public interface ILoginService {
    boolean buscarUsuario(int rut,String clave);
}
