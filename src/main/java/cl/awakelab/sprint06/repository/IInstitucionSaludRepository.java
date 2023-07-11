package cl.awakelab.sprint06.repository;

import cl.awakelab.sprint06.entity.InstitucionSalud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInstitucionSaludRepository extends JpaRepository<InstitucionSalud,Integer> {
}
