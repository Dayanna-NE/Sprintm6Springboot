package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario",nullable = false)
    private int idUsuario;
    @Column(unique = true,nullable = false)
    private int run;
    @Column(length = 200,nullable = false)
    private String clave;
    @Column(length = 100,nullable = false)
    private String nombre;
    @Column(name = "apellido_1",length = 100,nullable = false)
    private String apellido1;
    @Column(name = "apellido_2",length = 100)
    private String apellido2;
    // ID PERFIL
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil",nullable = false) //reemplaza el @Column
    private Perfil perfil;
    //FIN ID PERFIL
    @Column(length = 100,nullable = false)
    private String email;
    @Column(name="fecha_creacion",nullable = false)
    private LocalDate fechaCreacion;
    @Column(columnDefinition = "bigint")
    private long telefono;
    //enlazando con empleador
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Empleador> listarEmpleadores;
}
