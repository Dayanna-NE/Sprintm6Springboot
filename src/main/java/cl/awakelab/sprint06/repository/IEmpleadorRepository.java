package cl.awakelab.sprint06.repository;

import cl.awakelab.sprint06.entity.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadorRepository extends JpaRepository<Empleador,Integer> {
}
