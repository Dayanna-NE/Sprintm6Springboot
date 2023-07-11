package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.Liquidacion;

import java.util.List;

public interface ILiquidacionService {
    Liquidacion cerarLiquidacion(Liquidacion liquidacion);
    List<Liquidacion> listarLiquidacion();
    Liquidacion buscarLiquidacion(int idLiquidacion);
    Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar);
    void eliminarLiquidacion(int idLiquidacion);
}
