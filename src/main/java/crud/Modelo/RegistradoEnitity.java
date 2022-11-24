package crud.Modelo;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author Jose María
 * Clase modelo de los Clientes Registrados
 */
@Entity
@Table(name = "registrado", schema = "agendaproconsi", catalog = "")
public class RegistradoEnitity {

    @Basic
    @Column(name = "CuotaMaxPago",nullable = false)
    private BigInteger cuotaMaxPago;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClienteDNI",nullable = false)
    private String clienteDni;

    @OneToOne
    @JoinColumn(name = "ClienteDNI", referencedColumnName = "DNI", nullable = false)
    private ClienteEntity clientesByClienteDni;

    public BigInteger getCuotaMaxPago() {
        return cuotaMaxPago;
    }

    public void setCuotaMaxPago(BigInteger cuotaMaxPago) {
        this.cuotaMaxPago = cuotaMaxPago;
    }

    public String getClienteDni() {
        return clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistradoEnitity that = (RegistradoEnitity) o;
        return Objects.equals(cuotaMaxPago, that.cuotaMaxPago) && Objects.equals(clienteDni, that.clienteDni);
    }

    public ClienteEntity getClientesByClienteDni() {
        return clientesByClienteDni;
    }

    public void setClientesByClienteDni(ClienteEntity clientesByClienteDni) {
        this.clientesByClienteDni = clientesByClienteDni;
    }
}
