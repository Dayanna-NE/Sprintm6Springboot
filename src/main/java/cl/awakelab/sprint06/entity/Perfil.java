package cl.awakelab.sprint06.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "perfil")
public class Perfil{
    @Id
    @Column(name = "id_perfil",nullable = false)
    private Integer idPerfil;
    @Column(length = 50,nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private boolean estado;
    //Enlazando con Usuario
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<Usuario>listarUsuarios;
}
