package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioId(int idUsuario);
    Usuario actualizarUsuario(Usuario usuarioActualizar);
    void eliminarUsuario(int idUsuario);
}
