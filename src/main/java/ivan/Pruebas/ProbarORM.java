package ivan.Pruebas;

import ivan.Constructores.Guardado;
import ivan.Constructores.MeGusta;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

public class ProbarORM {
    public static void main(String[] args) {
        // Crear EntityManagerFactory
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("JPAIvan");
        // Crear EntityManager
        EntityManager em = enf.createEntityManager();

        try {
            // Inicio de transacción
            em.getTransaction().begin();

            // Crear instancias de entidades
            Usuario usuario1 = new Usuario();
            usuario1.setNombreUsuario ("usuario1");
            usuario1.setNombre ("Nombre1");
            usuario1.setApellidos ("Apellido1");
            usuario1.setLocalidad ("Localidad1");
            usuario1.setEmail ("email1@example.com");
            usuario1.setPassword ("password1");
            usuario1.setRol ("rol");

            Usuario usuario2 = new Usuario();
            usuario2.setNombreUsuario ("usuario2");
            usuario2.setNombre ("Nombre2");
            usuario2.setApellidos ("Apellido2");
            usuario2.setLocalidad ("Localidad2");
            usuario2.setEmail ("email2@example.com");
            usuario2.setPassword ("password2");
            usuario2.setRol ("rol");

            Publicacion publicacion1 = new Publicacion();
            publicacion1.setMensaje ("Mensaje de prueba 1");
            publicacion1.setUsuario (usuario1);
            publicacion1.setFecha (new Date ());

            Publicacion publicacion2 = new Publicacion();
            publicacion1.setMensaje ("Mensaje de prueba 4");
            publicacion1.setUsuario (usuario2);
            publicacion2.setFecha (new Date ());

            MeGusta meGusta1 = new MeGusta(usuario1, publicacion2);
            MeGusta meGusta2 = new MeGusta(usuario2, publicacion1);

            Guardado guardado1 = new Guardado(usuario1, publicacion2);
            Guardado guardado2 = new Guardado(usuario2, publicacion1);

            // Persistir objetos en la base de datos
            em.persist(usuario1);
            em.persist(usuario2);
            em.persist(publicacion1);
            em.persist(publicacion2);
            em.persist(meGusta1);
            em.persist(meGusta2);
            em.persist(guardado1);
            em.persist(guardado2);

            // Commit de la transacción
            em.getTransaction().commit();

            System.out.println("Datos insertados en la base de datos con éxito.");
        } catch (Exception e) {
            // Manejar excepciones
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar EntityManager
            em.close();
            // Cerrar EntityManagerFactory
            enf.close();
        }
    }
}

