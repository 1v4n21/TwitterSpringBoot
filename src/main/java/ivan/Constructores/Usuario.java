package ivan.Constructores;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Entity
@Table(name = "USUARIOS")
@Scope("prototype")  //Este componente con alcance prototype
@Data
// Lombok Genera automáticamente getters, setters, toString(), equals() y hashCode() para todos los campos de la clase.
@NoArgsConstructor //Lombok Genera un constructor sin argumentos.
@AllArgsConstructor  //Lombok Genera un constructor que acepta todos los campos
@Builder
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @Column(name = "idUsuario")
    private long idUsuario;

    @NotBlank(message = "El campo nombreUsuario no puede estar vacío")
    @Size(max = 20, message = "El nombre de usuario no puede tener mas de 20 caracteres")
    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "localidad")
    private String localidad;

    @NotBlank(message = "El campo email no puede estar vacío")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "El campo contraseña no puede estar vacío")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<MeGusta> meGustas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Guardado> guardados = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // Agregar el rol del usuario a la lista de autoridades
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol);
        authorityList.add(authority);

        return authorityList;
    }

    @Override
    public String getUsername () {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}
