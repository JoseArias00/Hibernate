package crud;

import java.time.LocalDateTime;

public class Socio extends Cliente {

    public Socio(String DNI, String nombre, String apellidos, CondicionesClientes condicion, LocalDateTime fechaAlta){
        super(DNI,nombre,apellidos,condicion,fechaAlta);
    }

}
