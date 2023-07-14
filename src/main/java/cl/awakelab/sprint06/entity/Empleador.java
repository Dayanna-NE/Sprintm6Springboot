package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "empleador")
public class Empleador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleador",nullable = false)
    private int idEmpleador;
    @Column(unique = true,nullable = false)
    private int run;
    @Column(length = 100,nullable = false)
    private String nombre;
    @Column(name = "apellido_1",length = 100,nullable = false)
    private String apellido1;
    @Column(name = "apellido_2",length = 100)
    private String apellido2;
    @Column(name = "direccion",columnDefinition = "text(500)")
    private String direccion;
    @Column(length = 100)
    private String email;
    //Id Usuario
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
    //Fin id usuario
    @Column(columnDefinition = "bigint")
    private long telefono;

    //emlazamos con trabajador Muchos a muchos
    @ManyToMany
    @JoinTable(name = "empl_trab", //nombre de la tabla Detalle
        joinColumns = @JoinColumn(name = "id_empleador"), //nombre de mi campo id_emleador en BD
            inverseJoinColumns = @JoinColumn(name = "id_trabajador")) //nombre de mi campo id_trabajado
    private List<Trabajador> listarTrabajadores;

}
