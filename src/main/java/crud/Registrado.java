package crud;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Registrado extends Cliente {
    private BigDecimal cuotaMaxPago;

    public Registrado(String DNI, String nombre, String apellidos, CondicionesClientes condicion, LocalDateTime fechaAlta, BigDecimal cuotaMaxPago){
        super(DNI,nombre,apellidos,condicion,fechaAlta);
        this.cuotaMaxPago = cuotaMaxPago;
    }

    public BigDecimal getCuotaMaxPago() {
        return cuotaMaxPago;
    }

    public void setCuotaMaxPago(BigDecimal cuotaMaxPago) {
        this.cuotaMaxPago = cuotaMaxPago;
    }
}

