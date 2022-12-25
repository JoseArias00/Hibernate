package crud.Controlador;

import crud.Servicio.UtileriaValidaciones;

/**
 * @author Jose Maria
 */
public class ControladorValidaciones {

    //Validaciones de ConsolaPrincipal

    /**
     * @param accion Accion a validar entre las mostradas por pantalla
     * @return True si la accion seleccionada es válida, false si no
     */
    public static boolean validarAcciones(final String accion) {
        if (accion != null) {
            return UtileriaValidaciones.validarAccion(accion);
        } else {
            return false;
        }
    }

    //Validaciones de ConsolaInsertar

    /**
     * @param DNI DNI/NIE/CIF a validar
     * @return True si es válido, false si no
     */
    public static boolean validarDNI(final String DNI) {
        if (DNI != null) {
            return UtileriaValidaciones.validarIdentificador(DNI);
        } else {
            return false;
        }
    }

    /**
     * @param fechaAlta La fecha que queremos validar
     * @return True si la fecha existe, false si no
     */
    public static boolean validarFecha(final String fechaAlta) {
        if (fechaAlta != null) {
            return UtileriaValidaciones.validarFecha(fechaAlta);
        } else {
            return false;
        }
    }

    /**
     * @param tipo El tipo de cliente a validar
     * @return True si el tipo existe, false si no
     */
    public static boolean validarTipo(final String tipo) {
        if (tipo != null) {
            return UtileriaValidaciones.validarTipo(tipo);
        } else {
            return false;
        }
    }

    /**
     * @param cuotaMaxima La cuota maxima a validar
     * @return True si la cuota máxima es un número correcto, false si no
     */
    public static boolean validarCuota(final String cuotaMaxima) {
        if (cuotaMaxima != null) {
            return UtileriaValidaciones.validarCuota(cuotaMaxima);
        } else {
            return false;
        }
    }

    //Validaciones de ConsolaBorrar

    /**
     * @param accion La accion a validar a la hora de borrar
     * @return True si es una acción válida, false si no lo es
     */
    public static boolean validarAccion(final String accion) {
        if (accion != null) {
            return UtileriaValidaciones.validarAccionBorrar(accion);
        } else {
            return false;
        }
    }

    //Validaciones de ConsolaEditar

    /**
     * @param opcion La opcion a validar si es 'Si' o 'No'
     * @return True si es un 'Si' o un 'No', false si no es ninguna de ellas
     */
    public static boolean validarSiNo(final String opcion) {
        if (opcion != null) {
            return UtileriaValidaciones.validarSiNo(opcion);
        } else {
            return false;
        }
    }

    /**
     * @param campos Los campos a validar de un Cliente
     * @return True si es un campo de un cliente, false si no
     */
    public static boolean validarCampos(final String campos) {
        if (campos != null) {
            return UtileriaValidaciones.validarCampos(campos);
        } else {
            return false;
        }
    }

    //Validaciones de ConsolaListar

    /**
     * @param orden El orden de ordenación a validar
     * @return True si se puede ordenar en ese orden, false si no
     */
    public static boolean validarOrden(final String orden) {
        if (orden != null) {
            return UtileriaValidaciones.validarOrden(orden);
        } else {
            return false;
        }
    }


}
