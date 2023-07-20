package cl.awakelab.sprint06.repository;


import cl.awakelab.sprint06.entity.Trabajador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrabajadorRepository extends JpaRepository<Trabajador,Integer> {
    Trabajador findByRun(int run);
}
