package crud.Vista;

import java.util.Scanner;

import static crud.Vista.UtileriaValidaciones.*;

public class ConsolaListar {

    public static void listarClientes() {
        //TODO
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea ordenar la lista?(Si,No)");

        String respuesta = sc.nextLine();

        while(!validarSiNo(respuesta)){
            System.err.println("Por favor, introduzca únicamente 'Si' o 'No.");
        }
    }
}
