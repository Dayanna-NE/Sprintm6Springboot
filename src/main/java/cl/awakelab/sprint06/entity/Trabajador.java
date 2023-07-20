package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "trabajador")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador",nullable = false)
    private int idTrabajador;
    @Column(nullable = false,unique = true)
    private Integer run;
    @Column(length = 100,nullable = false)
    private String nombre;
    @Column(length = 100,nullable = false,name = "apellido_1")
    private String apellido1;
    @Column(length = 100,name = "apellido_2")
    private String apellido2;
    @Column(length = 100)
    private String email;
    //ID institucionPrevension
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_prevision",nullable = false)
    private InstitucionPrevision institucionPrevision;
    //fin
    //ID institucionSalud
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud",nullable = false)
    private InstitucionSalud institucionSalud;
    //fin
    @Column(columnDefinition = "bigint")
    private Long telefono;
    //Conectando con Liquidacion
    @OneToMany(mappedBy = "trabajador",cascade = CascadeType.ALL)
    private List<Liquidacion> listarLiquidaciones;
    //Conectando con Empleado Muchos a muchos
    @ManyToMany(mappedBy = "listarTrabajadores")
    private List<Empleador> listarEmpleadores;
}
