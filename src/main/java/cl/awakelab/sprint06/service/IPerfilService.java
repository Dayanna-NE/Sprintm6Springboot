package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Perfil;

import java.util.List;

public interface IPerfilService {
    Perfil crearPerfil(Perfil perfil);
    List<Perfil> listarPerfil();
    Perfil buscarPerfil(int idPerfil);
    Perfil actualizarPerfil(Perfil perfilActualizar);
    void eliminarPerfil(int idPerfil);
}
