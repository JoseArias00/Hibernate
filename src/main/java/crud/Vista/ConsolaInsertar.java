package crud.Vista;

import crud.Controlador.Controlador;
import crud.Excepciones.ClienteException;
import crud.Modelo.ClienteEntity;

import java.math.BigDecimal;
import java.util.Scanner;

import static crud.Vista.UtileriaValidaciones.*;

public class ConsolaInsertar {

    public static void insertarClientes() {
        ClienteEntity clienteEntity = new ClienteEntity();
        Scanner sc = new Scanner(System.in);

        //Asignamos el identificador del cliente
        System.out.println("Introduzca el DNI/NIE/CIF del cliente:");

        String identificador = sc.nextLine();

        while (!validarIdentificador(identificador)) {
            System.err.println("El DNI/NIE/CIF no puede ser nulo, por favor, introdúzcalo de nuevo:");
            identificador = sc.nextLine();
        }

        clienteEntity.setDni(identificador);

        //Asignamos el nombre del cliente
        System.out.println("Introduzca el nombre del cliente:");

        String nombre = sc.nextLine();

        while (nombre.equals("")) {
            System.err.println("El nombre no puede ser nulo, por favor, introdúzcalo de nuevo:");
            nombre = sc.nextLine();
        }

        clienteEntity.setNombre(nombre);

        //Asignamos los apellidos del cliente
        System.out.println("Introduzca los apellidos del cliente:");

        String apellidos = sc.nextLine();

        while (apellidos.equals("")) {
            System.err.println("Los apellidos no puede ser nulo, por favor, introdúzcalo de nuevo:");

            apellidos = sc.nextLine();
        }

        clienteEntity.setApellidos(apellidos);

        //Asignamos la fecha de alta del cliente
        System.out.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");


        String fechaAlta = sc.nextLine();

        while (!validarFecha(fechaAlta)) {
            System.err.println("Introduzca la fecha de alta del cliente (yyyy/MM/dd HHmmss):");

            fechaAlta = sc.nextLine();
        }

        clienteEntity.setFechaAlta(devolverFechaAlta(fechaAlta));

        //Asignamos el tipo de cliente
        System.out.println("Introduzca el tipo del cliente:"
                + "\n0.Registrado"
                + "\n1.Socio");


        String tipo = sc.nextLine();

        while (!validarTipo(tipo)) {
            System.err.println("El tipo no es válido, por favor, introdúzcalo de nuevo:");

            tipo = sc.nextLine();
        }

        clienteEntity.setTipo(Byte.parseByte(tipo));

        //Asignamos la cuota máxima del cliente cuando sea necesaria
        if (Byte.parseByte(tipo) == 0) {
            System.out.println("Introduzca la cuota máxima del cliente:");


            String cuota = sc.nextLine();

            while (!validarCuota(cuota)) {
                System.err.println("La cuota máxima no es válida, por favor, introdúzcala de nuevo:");

                cuota = sc.nextLine();
            }

            BigDecimal cuotaMax = new BigDecimal(cuota);
            clienteEntity.setCuotaMaxima(cuotaMax);
        }

        try{
            Controlador.insertarCliente(clienteEntity);
        } catch (ClienteException exception){
            System.err.println("El cliente no se ha podido añadir por errores de validaciones.");
        }
    }

}
