package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.Trabajador;
import cl.awakelab.sprint06.entity.Usuario;
import cl.awakelab.sprint06.repository.ITrabajadorRepository;
import cl.awakelab.sprint06.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("trabajadorImpl")
public class TrabajadorImpl implements ITrabajadorService {
    @Autowired
    ITrabajadorRepository objTrabajadorRepo;
    @Override
    public Trabajador crearTrabajador(Trabajador trabajador) {
        return objTrabajadorRepo.save(trabajador);
    }

    @Override
    public List<Trabajador> listarTrabajador() {
        return objTrabajadorRepo.findAll();
    }

    @Override
    public Trabajador buscarTrabajador(int idTrabajador) {
        return objTrabajadorRepo.findById(idTrabajador).orElseThrow(()->new NoSuchElementException("No se encontro Trabajador"));
    }

    @Override
    public Trabajador actualizarTrabajador(Trabajador trabajadorActualizar) {
        Trabajador trabajador = buscarTrabajador(trabajadorActualizar.getIdTrabajador());
        trabajador.setNombre(trabajadorActualizar.getNombre());
        trabajador.setApellido1(trabajadorActualizar.getApellido1());
        trabajador.setApellido2(trabajadorActualizar.getApellido2());
        trabajador.setEmail(trabajadorActualizar.getEmail());
        trabajador.setTelefono(trabajadorActualizar.getTelefono());
        trabajador.setListarEmpleadores(trabajadorActualizar.getListarEmpleadores());
        trabajador.setInstitucionSalud(trabajadorActualizar.getInstitucionSalud());
        trabajador.setInstitucionPrevision(trabajadorActualizar.getInstitucionPrevision());
        return objTrabajadorRepo.save(trabajador);
    }

    @Override
    public void eliminarTrabajador(int idTrabajador) {
        objTrabajadorRepo.deleteById(idTrabajador);
    }

    @Override
    public boolean buscarTrabajadorRun(int runTrabajador) {
        Trabajador trabajador = objTrabajadorRepo.findByRun(runTrabajador);
        //buscando run
        if (trabajador!=null){
            return true;//encontro usuario con el run
        }
        return false;//no encontro usuario con el run
    }

}
