package crud.Controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.*;

public class UtileríaControlador {

    private static final Logger LOGGER = LogManager.getLogger(UtileríaControlador.class);

    public static boolean validarAccion(final String opcion) {
        if (opcion != null) {
            String opciones = "[1-6]{1}$";

            return Pattern.matches(opciones, opcion);
        }
        return false;
    }

    public static Timestamp devolverFechaAlta(final String fechaAlta) {
        if (fechaAlta != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmmss");

            try{
                LocalDateTime localDateTime = LocalDateTime.parse(fechaAlta, formatter);

                return Timestamp.valueOf(localDateTime);
            } catch (DateTimeParseException exception){
                if(LOGGER.isErrorEnabled()){
                    LOGGER.error("El formato de fecha introducido es erróneo. Vuelva a introducirlo");
                }

                return null;
            }

        }

        return null;
    }

    public static boolean validarTipo(final String tipo){
        if (tipo != null) {
            String opciones = "[0,1]{1}$";

            return Pattern.matches(opciones, tipo);
        }
        return false;
    }
}
