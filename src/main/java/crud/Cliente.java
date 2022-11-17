package crud;

import java.time.LocalDateTime;

public abstract class Cliente {
    private String DNI;
    private String nombre;
    private String apellidos;
    private Enum<CondicionesClientes> condicion;
    private LocalDateTime fechaAlta;

    public Cliente(){

    }

    public Cliente(String DNI, String nombre, String apellidos, CondicionesClientes condicion, LocalDateTime fechaAlta){
        this.DNI=DNI;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.condicion=condicion;
        this.fechaAlta=fechaAlta;
    }

    public static void anadirCliente(Cliente cliente){
        anadirCliente(cliente);
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public Enum<CondicionesClientes> getCondicion() {
        return condicion;
    }

    public void setCondicion(Enum<CondicionesClientes> condicion) {
        this.condicion = condicion;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}

