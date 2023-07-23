package cl.awakelab.sprint06.repository;

import cl.awakelab.sprint06.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILiquidacionRepository extends JpaRepository<Liquidacion,Long> {
}
