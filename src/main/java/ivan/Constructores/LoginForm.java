package ivan.Constructores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginForm {
    @NotBlank(message = "El campo nombreUsuario no puede estar vacío")
    @Size(max = 20, message = "El nombre de usuario no puede tener mas de 20 caracteres")
    private String nombreUsuario;

    @NotBlank(message = "El campo contraseña no puede estar vacío")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    public String getNombreUsuario () {
        return nombreUsuario;
    }

    public void setNombreUsuario (String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
