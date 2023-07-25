package cl.awakelab.sprint06.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario",nullable = false)
    private Integer idUsuario;
    @Column(unique = true,nullable = false)
    private Integer run;
    @Column(length = 200,nullable = false)
    private String clave;
    @Column(length = 100,nullable = false)
    private String nombre;
    @Column(name = "apellido_1",length = 100,nullable = false)
    private String apellido1;
    @Column(name = "apellido_2",length = 100)
    private String apellido2;
    // ID PERFIL
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //para poder escribir en formato Json
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil",nullable = false) //reemplaza el @Column
    private Perfil perfil;
    //FIN ID PERFIL
    @Column(length = 100,nullable = false)
    private String email;
    @Column(name="fecha_creacion",nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(columnDefinition = "bigint")
    private Long telefono;
    //enlazando con empleador
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Empleador> listarEmpleadores;
}
