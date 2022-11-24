package crud.Modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Jose María
 * Clase modelo de los Clientes
 */
@Entity
@Table(name = "clientes", schema = "agendaproconsi", catalog = "")
public class ClienteEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DNI",nullable = false)
    private String dni;

    @Column(name = "Nombre",nullable = false)
    private String nombre;

    @Column(name = "Apellidos",nullable = false)
    private String apellidos;

    @Column(name = "FechaAlta",nullable = false)
    private Timestamp fechaAlta;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(fechaAlta, that.fechaAlta);
    }

}
