package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="institucion_salud")
public class InstitucionSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inst_salud",nullable = false)
    private int idInstSalud;
    @Column(nullable = false, length = 50)
    private String descripcion;
    @Column(name = "proc_dcto", nullable = false,columnDefinition = "float")
    private float procDcto;
    //enlazando con trabajador
    @OneToMany(mappedBy = "institucionSalud",cascade = CascadeType.ALL)
    private List<Trabajador> listarTrabajadores;
    //enlazar con liquidacion
    @OneToMany(mappedBy = "institucionSalud",cascade = CascadeType.ALL)
    private List<Liquidacion> listarLiquidaciones;
}
