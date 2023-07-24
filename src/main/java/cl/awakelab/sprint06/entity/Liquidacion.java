package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "liquidacion")
public class Liquidacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liquidacion",columnDefinition = "bigint",nullable = false)
    private Long idLiquidacion;
    //---------------------------------------Id trabajador-------------------
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trabajador")
    private Trabajador trabajador;
    //Fin id trabajador
    @Column(nullable = false)
    private LocalDateTime periodo;
    @Column(name = "sueldo_imponible",nullable = false)
    private Integer sueldoImponible;
    @Column(name = "sueldo_liquido",nullable = false)
    private Integer sueldoLiquido;
    //id institucion salud
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud",nullable = false)
    private InstitucionSalud institucionSalud;
    //fin id institucion salud
    @Column(name = "monto_inst_salud",nullable = false)
    private Integer montoInstSalud;
    //id institucion provisional
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_previsional",nullable = false)
    private InstitucionPrevision institucionPrevision;
    //fin institucion provisional
    //fin id institucion provisional
    @Column(name = "monto_inst_previsional",nullable = false)
    private Integer montoInstPrevisional;
    @Column(name = "total_descuento",nullable = false)
    private Integer totalDescuento;
    @Column(name="total_haberes",nullable = false)
    private Integer totalHaberes;
    @Column(nullable = false)
    private Integer anticipo;

}
