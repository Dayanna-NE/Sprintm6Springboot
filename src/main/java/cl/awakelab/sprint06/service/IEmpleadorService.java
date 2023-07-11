package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Empleador;

import java.util.List;

public interface IEmpleadorService {
    Empleador cerarEmpleador(Empleador empleador);
    List<Empleador> listarEmpleador();
    Empleador buscarEmpleador(int idEmpleador);
    Empleador actualizarEmpleador(Empleador empleadorActualizar);
    void eliminarEmpleador(int idEmpleador);
}
