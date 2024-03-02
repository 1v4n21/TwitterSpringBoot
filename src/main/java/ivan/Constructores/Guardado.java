package ivan.Constructores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "GUARDADOS")
@Scope("singleton")  //Este componente con alcance singleton
@Data
// Lombok Genera automáticamente getters, setters, toString(), equals() y hashCode() para todos los campos de la clase.
@NoArgsConstructor //Lombok Genera un constructor sin argumentos.
@AllArgsConstructor  //Lombok Genera un constructor que acepta todos los campos
@Builder
public class Guardado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guardado_seq")
    @SequenceGenerator(name = "guardado_seq", sequenceName = "GUARDADO_SEQ", allocationSize = 1)
    @Column(name = "idGuardado")
    private long idGuardado;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPublicacion", referencedColumnName = "idPublicacion")
    private Publicacion publicacion;

    public Guardado(Usuario usuario, Publicacion publicacion) {
        this.usuario = usuario;
        this.publicacion = publicacion;
    }
}
