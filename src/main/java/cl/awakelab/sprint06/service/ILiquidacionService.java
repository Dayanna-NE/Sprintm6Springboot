package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.InstitucionPrevision;
import cl.awakelab.sprint06.entity.InstitucionSalud;
import cl.awakelab.sprint06.entity.Liquidacion;

import java.util.List;

public interface ILiquidacionService {
    Liquidacion crearLiquidacion(Liquidacion liquidacion);
    List<Liquidacion> listarLiquidacion();
    Liquidacion buscarLiquidacion(Long idLiquidacion);
    Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar);
    void eliminarLiquidacion(Long idLiquidacion);
    Liquidacion calcularLiquidacion(Liquidacion liquidacion, InstitucionPrevision institucionPrevision,
                                    InstitucionSalud institucionSalud);
}
