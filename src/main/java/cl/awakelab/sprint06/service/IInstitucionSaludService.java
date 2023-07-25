package cl.awakelab.sprint06.service;

import cl.awakelab.sprint06.entity.InstitucionSalud;

import java.util.List;

public interface IInstitucionSaludService {
    InstitucionSalud crearInstitucionSalud(InstitucionSalud institucionSalud);
    List<InstitucionSalud> listarInstitucionSalud();
    InstitucionSalud buscarInstitucionSalud(int idInstitucionSalud);
    InstitucionSalud actualizarInstitucionSalud(InstitucionSalud institucionSaludActualizar);
    void eliminarInstitucionSalud(int idInstitucionSalud);
}
