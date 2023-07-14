package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
//institucion_prevision
@Data
@Entity
@Table(name = "institucion_prevision")
public class InstitucionPrevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inst_prevision",nullable = false)
    private int idInstPrevision;
    @Column(nullable = false,length = 50)
    private String descripcion;
    @Column(name = "porc_dcto", nullable = false, columnDefinition = "float")
    private float proDcto;
    //enlazando con trabajador
    @OneToMany(mappedBy = "institucionPrevision", cascade = CascadeType.ALL)
    private List<Trabajador> listarTrabajadores;
    //enlazando con Liquidacion
    @OneToMany(mappedBy = "institucionPrevision", cascade = CascadeType.ALL)
    private List<Liquidacion> listarLiquidaciones;


}
