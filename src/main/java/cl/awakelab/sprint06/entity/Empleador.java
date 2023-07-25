package cl.awakelab.sprint06.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer idEmpleador;
    @Column(unique = true,nullable = false)
    private Integer run;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //para poder escribir en formato Json
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
    //Fin id usuario
    @Column(columnDefinition = "bigint")
    private Long telefono;

    @ManyToMany(mappedBy = "listarEmpleadores")
    private List<Trabajador> listarTrabajadores;

}
