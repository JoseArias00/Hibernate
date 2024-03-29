package crud.Servicio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * @author Jose Maria
 */
public class UtileriaValidaciones {
    private static final Logger LOGGER = LogManager.getLogger(ClienteServicio.class);

    /**
     * @param opcion La opción elegida por el usuario
     * @return True si la opción esta comprendida entre 1 y 6, false si no
     */
    public static boolean validarAccion(final String opcion) {
        if (opcion != null) {
            String opciones = "[1-6]{1}$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    /**
     * @param opcion La opción elegida por el usuario
     * @return True si el orden es "Fecha de alta" o "DNI", false si no
     */
    public static boolean validarOrden(final String opcion) {
        if (opcion != null) {
            String opciones = "(Fecha de alta|DNI)$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    /**
     * @param opcion La opción elegida por el usuario
     * @return True si la opción es 1 o 2, false si no lo es
     */
    public static boolean validarAccionBorrar(final String opcion) {
        if (opcion != null) {
            String opciones = "[1-2]{1}$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    /**
     * @param opcion La opción elegida por el usuario
     * @return True si la opcion es Si o No, false si no es ninguna de ellas
     */
    public static boolean validarSiNo(final String opcion) {
        if (opcion != null) {
            String opciones = "(Si|No)$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    /**
     * @param opcion La opción elegida por el usuario
     * @return True si la opcion esta comprendida entre 1 y 5, false si no
     */
    public static boolean validarCampos(final String opcion) {
        if (opcion != null) {
            String opciones = "[1-5]$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    /**
     * @param fechaAlta La fecha de alta a transformar a timeStamp según un formato
     * @return La fecha de alta en timestamp
     */
    public static Timestamp devolverFechaAlta(final String fechaAlta) {
        if (fechaAlta != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmmss");

            try {
                LocalDateTime localDateTime = LocalDateTime.parse(fechaAlta, formatter);

                return Timestamp.valueOf(localDateTime);
            } catch (DateTimeParseException exception) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Fecha introducida errónea.");
                }

                return null;
            }
        }

        return null;
    }

    /**
     * @param fechaAlta La fecha de alta a validar
     * @return True si la fecha de alta es válida, false si no lo és
     */
    public static boolean validarFecha(final String fechaAlta) {
        if (fechaAlta.length() == 17 && fechaAlta != null) {
            String anio = "^\\d+";
            String anioBisiestos = "((01/([012][\\d]|3[01])|" +
                    "(02/([01][\\d]|2[0123456789]))|" +
                    "(03/([012][\\d]|3[01]))|" +
                    "(04/([012][\\d]|3[0]))|" +
                    "(05/([012][\\d]|3[01]))|" +
                    "(06/([012][\\d]|3[0]))|" +
                    "(07/([012][\\d]|3[01]))|" +
                    "(08/([012][\\d]|3[01]))|" +
                    "(09/([012][\\d]|3[0]))|" +
                    "(10/([012][\\d]|3[01]))|" +
                    "(11/([012][\\d]|3[0]))|" +
                    "(12/([012][\\d]|3[01]))))";
            String aniosNoBisiestos = "((01/([012][\\d]|3[01])|" +
                    "(02/([01][\\d]|2[012345678]))|" +
                    "(03/([012][\\d]|3[01]))|" +
                    "(04/([012][\\d]|3[0]))|" +
                    "(05/([012][\\d]|3[01]))|" +
                    "(06/([012][\\d]|3[0]))|" +
                    "(07/([012][\\d]|3[01]))|" +
                    "(08/([012][\\d]|3[01]))|" +
                    "(09/([012][\\d]|3[0]))|" +
                    "(10/([012][\\d]|3[01]))|" +
                    "(11/([012][\\d]|3[0]))|" +
                    "(12/([012][\\d]|3[01]))))";
            String horas = "\\s(([0-1]\\d)|(2[0-3]))([0-5]\\d){2}$";

            String[] datos = fechaAlta.substring(0, fechaAlta.length() - 7).split("/");
            int bisiesto = Integer.valueOf(datos[0]) % 4;

            String fecha = (bisiesto == 0) ? anio + "/" + anioBisiestos + horas : anio + "/" + aniosNoBisiestos + horas;

            return Pattern.matches(fecha, fechaAlta);
        }

        return false;
    }

    /**
     * @param tipo El tipo elegido por el usuario
     * @return True si el tipo es 0 o 1, false si no es ninguno
     */
    public static boolean validarTipo(final String tipo) {
        if (tipo != null) {
            String opciones = "[0,1]{1}$";

            return Pattern.matches(opciones, tipo);
        }
        return false;
    }

    /**
     * @param cuota La cuota máxima a validar
     * @return True si es convertible a bid decimal y es correcta, false si no
     */
    public static boolean validarCuota(final String cuota) {
        if (cuota != null) {
            try {
                BigDecimal cuotaMax = new BigDecimal(cuota);

                return true;
            } catch (NumberFormatException exception) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Formato de cuota máxima introducida erróneo.");
                }

                return false;
            }

        }
        return false;
    }

    /**
     * @param identificador El identificador a validar
     * @return True si el identificador es válido y correcto, false si no
     * @throws NullPointerException Ocurre cuando se le pasa un identificador nulo por parámetro
     */
    public static boolean validarIdentificador(final String identificador) throws NullPointerException {
        if (identificador == null) {
            return false;
        }

        //TODO validar Cif
        String DNI = "\\d{8}[a-zA-Z]$";
        String NIE = "[x-zX-Z]\\d{7}[A-HJ-NP-TV-Za-hj-np-tv-z]$";
        String CIF = "^[a-zA-Z^IiÑñOoTtXxYyZz]([:digit:]{0,7}([abcdefghijABCDEFGHIJ]|[:digit:]))$";
        boolean correcto = false;

        if (Pattern.matches(DNI, identificador)) {
            if (validarLetraDNI(identificador)) {
                correcto = true;
            }
        } else if (Pattern.matches(NIE, identificador)) {
            if (validarNIE(identificador)) {
                correcto = true;
            }
        }

        return correcto;
    }

    /**
     * @param NIE El NIE del que calcularemos su dígito de control
     * @return True si es correcto el dígito de control, y false si no lo es
     */
    private static boolean validarNIE(String NIE) {
        String x = "X";
        String y = "Y";
        String X = "0";
        String Y = "1";
        String Z = "2";

        if (NIE.toUpperCase().charAt(0) == x.charAt(0)) {
            NIE = X + NIE.substring(1, 9);
            return validarLetraDNI(NIE);
        } else if (NIE.toUpperCase().charAt(0) == y.charAt(0)) {
            NIE = Y + NIE.substring(1, 9);
            return validarLetraDNI(NIE);
        } else {
            NIE = Z + NIE.substring(1, 9);
            return validarLetraDNI(NIE);
        }
    }

    /**
     * @param DNI El DNI que queremos validar
     * @return Si el DNI es válido o no. True si lo es, y false si no
     */
    private static boolean validarLetraDNI(final String DNI) {
        int numerosDNI = Integer.parseInt(DNI.substring(0, 8));
        int resto;
        char miLetra;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        resto = numerosDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra == Character.toUpperCase(DNI.charAt(8));
    }

}
