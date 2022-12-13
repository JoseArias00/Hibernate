package crud.Excepciones;

/**
 * @author Jose María
 * <p>
 * Excepción creada para cuando se produce una incoherencia entre el tipo de cliente y la cuota máxima asignada
 */
public class ClienteTipoException extends Exception {
    public ClienteTipoException(String msg) {
        super(msg);
    }
}
