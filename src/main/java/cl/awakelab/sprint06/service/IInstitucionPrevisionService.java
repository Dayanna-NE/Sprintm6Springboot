package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.InstitucionPrevision;

import java.util.List;

public interface IInstitucionPrevisionService {
    InstitucionPrevision crearInstitucionPrevision(InstitucionPrevision institucionPrevision);
    List<InstitucionPrevision> listarInstitucionPrevision();
    InstitucionPrevision buscarInstitucionPrevision(int idInstitucionPrevision);
    InstitucionPrevision actualizarInstitucionPrevision(InstitucionPrevision institucionPrevisionActualizar);
    void eliminarInstitucionPrevision(int idInstitucionPrevision);
}
