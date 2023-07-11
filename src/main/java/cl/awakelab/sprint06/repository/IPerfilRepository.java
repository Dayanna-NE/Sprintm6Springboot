package cl.awakelab.sprint06.repository;

import cl.awakelab.sprint06.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil,Integer> {
}
