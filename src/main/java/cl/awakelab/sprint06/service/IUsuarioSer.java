package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Usuario;

import java.util.List;

public interface IUsuarioSer {
    //Actualizar o update
    Usuario actualizarUsuario(Usuario usuario);
    //Delete o eliminar
    void elimarUsuario(int idUsuario);
    //Busucar usuario por id
    Usuario buscarUsuarioPorID(int idUsuario);
    //Buscar Todos los usuario
    List<Usuario> buscarTodosLosUsuario();
    //Registrar Usuario
    Usuario registrarUsaurio(Usuario usuario);

}
