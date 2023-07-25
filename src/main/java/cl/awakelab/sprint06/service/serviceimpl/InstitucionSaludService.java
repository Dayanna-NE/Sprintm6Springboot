package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.InstitucionSalud;
import cl.awakelab.sprint06.repository.IInstitucionSaludRepository;
import cl.awakelab.sprint06.service.IInstitucionSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("institucionSaludImpl")
public class InstitucionSaludService implements IInstitucionSaludService {
    @Autowired
    IInstitucionSaludRepository objInstSaludRepo;

    @Override
    public InstitucionSalud crearInstitucionSalud(InstitucionSalud institucionSalud) {
        return objInstSaludRepo.save(institucionSalud);
    }

    @Override
    public List<InstitucionSalud> listarInstitucionSalud() {
        return objInstSaludRepo.findAll();
    }

    @Override
    public InstitucionSalud buscarInstitucionSalud(int idInstitucionSalud) {
        return objInstSaludRepo.findById(idInstitucionSalud).orElseThrow(()->new NoSuchElementException("No se encontro Institucion de Salud"));
    }

    @Override
    public InstitucionSalud actualizarInstitucionSalud(InstitucionSalud institucionSaludActualizar) {
        InstitucionSalud institucionSalud=buscarInstitucionSalud(institucionSaludActualizar.getIdInstSalud());
        return objInstSaludRepo.save(institucionSaludActualizar);
    }

    @Override
    public void eliminarInstitucionSalud(int idInstitucionSalud) {
        objInstSaludRepo.deleteById(idInstitucionSalud);
    }
}
