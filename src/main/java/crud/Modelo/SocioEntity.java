package crud.Modelo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Jose María
 * Clase modelo de los Clientes Socios
 */
@Entity
@Table(name = "socio", schema = "agendaproconsi", catalog = "")
public class SocioEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClienteDNI",nullable = false)
    private String clienteDni;

    @OneToOne
    @JoinColumn(name = "ClienteDNI", referencedColumnName = "DNI", nullable = false)
    private ClienteEntity clientesByClienteDni;

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
        SocioEntity that = (SocioEntity) o;
        return Objects.equals(clienteDni, that.clienteDni);
    }

    public ClienteEntity getClientesByClienteDni() {
        return clientesByClienteDni;
    }

    public void setClientesByClienteDni(ClienteEntity clientesByClienteDni) {
        this.clientesByClienteDni = clientesByClienteDni;
    }
}
