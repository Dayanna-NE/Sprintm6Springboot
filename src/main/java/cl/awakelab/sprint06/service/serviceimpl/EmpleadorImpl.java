package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.Empleador;
import cl.awakelab.sprint06.repository.IEmpleadorRepository;
import cl.awakelab.sprint06.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("empleadoImpl")
public class EmpleadorImpl implements IEmpleadorService {
    @Autowired
    IEmpleadorRepository objEmpleadorRepo;

    @Override
    public Empleador cerarEmpleador(Empleador empleador) {
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public List<Empleador> listarEmpleador() {
        return objEmpleadorRepo.findAll();
    }

    @Override
    public Empleador buscarEmpleador(int idEmpleador) {
        return objEmpleadorRepo.findById(idEmpleador).orElseThrow(()->new NoSuchElementException("No se encontro Empleador"));
    }

    @Override
    public Empleador actualizarEmpleador(Empleador empleadorActualizar) {
        Empleador empleador = buscarEmpleador(empleadorActualizar.getIdEmpleador());
        return objEmpleadorRepo.save(empleadorActualizar);
    }

    @Override
    public void eliminarEmpleador(int idEmpleador) {
        objEmpleadorRepo.deleteById(idEmpleador);
    }

    @Override
    public boolean buscarEmpleadorRun(int runEmpleador) {
        Empleador empleador = objEmpleadorRepo.findByRun(runEmpleador);
        if (empleador != null){
            return true;
        }
        return false;
    }

}
