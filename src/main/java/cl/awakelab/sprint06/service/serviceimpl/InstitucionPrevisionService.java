package cl.awakelab.sprint06.service.serviceimpl;

import cl.awakelab.sprint06.entity.InstitucionPrevision;
import cl.awakelab.sprint06.repository.IInstitucionPrevisionRepository;
import cl.awakelab.sprint06.service.IInstitucionPrevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("institucionPrevisionImpl")
public class InstitucionPrevisionService implements IInstitucionPrevisionService {
    @Autowired
    IInstitucionPrevisionRepository objInstPrevisionRepo;

    @Override
    public InstitucionPrevision crearInstitucionPrevision(InstitucionPrevision institucionPrevision) {
        return objInstPrevisionRepo.save(institucionPrevision);
    }

    @Override
    public List<InstitucionPrevision> listarInstitucionPrevision() {
        return objInstPrevisionRepo.findAll();
    }

    @Override
    public InstitucionPrevision buscarInstitucionPrevision(int idInstitucionPrevision) {
        return objInstPrevisionRepo.findById(idInstitucionPrevision).orElseThrow(()->new NoSuchElementException("No se encontro la insitucion de prevencion"));
    }

    @Override
    public InstitucionPrevision actualizarInstitucionPrevision(InstitucionPrevision institucionPrevisionActualizar) {
        InstitucionPrevision institucionPrevision = buscarInstitucionPrevision(institucionPrevisionActualizar.getIdInstPrevision());
        return objInstPrevisionRepo.save(institucionPrevisionActualizar);
    }

    @Override
    public void eliminarInstitucionPrevision(int idInstitucionPrevision) {
        objInstPrevisionRepo.deleteById(idInstitucionPrevision);
    }
}
