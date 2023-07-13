package cl.awakelab.sprint06.repository;

import cl.awakelab.sprint06.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoginRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByRunAndClave(int run, String clave);
}
