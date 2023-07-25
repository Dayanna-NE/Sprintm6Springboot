package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.Liquidacion;
import cl.awakelab.sprint06.repository.ILiquidacionRepository;
import cl.awakelab.sprint06.service.ILiquidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {
    @Autowired
    ILiquidacionRepository objLiquidacionRepo;

    @Override
    public Liquidacion crearLiquidacion(Liquidacion liquidacion) {
        liquidacion.setPeriodo(LocalDateTime.now());
        if (liquidacion.getAnticipo()==null){
            liquidacion.setAnticipo(0);
        }
        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public List<Liquidacion> listarLiquidacion() {
        return objLiquidacionRepo.findAll();
    }

    @Override
    public Liquidacion buscarLiquidacion(Long idLiquidacion) {
        return objLiquidacionRepo.findById(idLiquidacion).orElseThrow(()->new NoSuchElementException("No se encontro Liquidacion"));
    }

    @Override
    public Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar) {
        Liquidacion liquidacion = buscarLiquidacion(liquidacionActualizar.getIdLiquidacion());
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setMontoInstSalud(liquidacionActualizar.getMontoInstSalud());
        liquidacion.setMontoInstPrevisional(liquidacionActualizar.getMontoInstPrevisional());
        liquidacion.setTotalDescuento(liquidacionActualizar.getTotalDescuento());
        liquidacion.setTotalHaberes(liquidacionActualizar.getTotalHaberes());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());
        liquidacion.setSueldoLiquido(liquidacionActualizar.getSueldoLiquido());
        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacion(Long idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
