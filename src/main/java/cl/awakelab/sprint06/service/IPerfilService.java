package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Perfil;
import cl.awakelab.sprint06.entity.Usuario;

import java.util.List;

public interface IPerfilService {
    Perfil cerarPerfil(Perfil perfil);
    List<Perfil> listarPerfil();
    Perfil buscarPerfil(int idPerfil);
    Perfil actualizarPerfil(Usuario perfilActualizar);
    void eliminarPerfil(int idPerfil);
}
