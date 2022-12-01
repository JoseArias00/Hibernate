package crud.Servicio;

import crud.Modelo.ClienteEntity;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.*;


public class UtileríaClienteServicio {

    public static void validarCampos(final ClienteEntity cliente) throws NullPointerException {
        if (cliente.equals(null)) {
            throw new NullPointerException("El cliente pasado por parámetro es nulo.");
        } else {
            if (cliente.getDni().equals(null)) {
                throw new NullPointerException("El DNI del cliente es nulo.");
            }
            if (cliente.getNombre().equals(null)) {
                throw new NullPointerException("El nombre del cliente es nulo.");
            }
            if (cliente.getApellidos().equals(null)) {
                throw new NullPointerException("El apellido del cliente es nulo.");
            }
            if (cliente.getFechaAlta().equals(null)) {
                throw new NullPointerException("La fecha de alta del cliente es nulo.");
            }
            if (!((byte) cliente.getTipo() == 0 || (byte) cliente.getTipo() == 1)) {
                throw new NullPointerException("El tipo de cliente es nulo.");
            }
            if((byte)cliente.getTipo()==0 && cliente.getCuotaMaxima() == null){
                throw new NullPointerException("La cuota máxima del cliente de tipo registrado es null");
            }
        }
    }

    public static boolean validarIdentificador(final String identificador) {
        if (identificador.equals(null)) {
            throw new NullPointerException("El identificador pasado por parámetro es nulo");
        }

        //TODO Cif
        String DNI = "\\d{8}[a-zA-Z]$";
        String NIE = "[x|y|z|X|Y|Z]\\d{7}[A-HJ-NP-TV-Za-hj-np-tv-z]$";

        if (Pattern.matches(DNI, identificador)) {
            return validarLetraDNI(identificador);
        }

        return false;
    }

    private static boolean validarLetraDNI(final String identificador) {
        int numerosDNI = Integer.parseInt(identificador.substring(0, 8));
        int resto;
        char miLetra;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        resto = numerosDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra == Character.toUpperCase(identificador.charAt(8));
    }
}
