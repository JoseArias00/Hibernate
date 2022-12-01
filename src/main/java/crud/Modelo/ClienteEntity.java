package crud.Modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "clientes", schema = "agendaproconsi")
public class ClienteEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClienteID")
    private int clienteId;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "FechaAlta")
    private Timestamp fechaAlta;

    @Column(name = "Tipo")
    private byte tipo;

    @Column(name = "CuotaMaxima")
    private BigDecimal cuotaMaxima;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

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

    public byte getTipo() {
        return tipo;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getCuotaMaxima() {
        return cuotaMaxima;
    }

    public void setCuotaMaxima(BigDecimal cuotaMaxima) {
        this.cuotaMaxima = cuotaMaxima;
    }

    public ClienteEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return clienteId == that.clienteId && tipo == that.tipo && Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(fechaAlta, that.fechaAlta) && Objects.equals(cuotaMaxima, that.cuotaMaxima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, dni, nombre, apellidos, fechaAlta, tipo, cuotaMaxima);
    }
}
