package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.entity.Usuario;

import java.util.List;

public interface ITrabajadorService {
    Trabajador crearTrabajador(Trabajador trabajador);
    List<Trabajador> listarTrabajador();
    Trabajador buscarTrabajador(int idTrabajador);
    Usuario actualizarTrabajador(Trabajador trabajadorActualizar);
    void eliminarTrabajador(int idTrabajador);
}
