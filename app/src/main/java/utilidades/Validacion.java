package utilidades;

/**
 * Creado por SHABAB
 * Modificado por  Saleta el 11/17/2015.
 */
import android.widget.EditText;
import java.util.regex.Pattern;

public class Validacion {

    private static Validacion instance = null;
    protected Validacion() {

    }
    public static Validacion getInstance() {
        if(instance == null) {
            instance = new Validacion();
        }
        return instance;
    }

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "8[024]9-\\d{3}-\\d{4}";
    private static final String NOMBRE_REGEX = "([a-z]|[A-Z])+(([a-z]|[A-Z])|\\ )*";
    private static final String USUARIO_REGEX = "([a-z])([a-z]|[0-9]|_)*";

    // Mensajes de errores.
    private static final String REQUERIDO_MSG = "Este campo es necesario";
    private static final String EMAIL_MSG = "Correo Inválido";
    private static final String TEL_MSG = "Utilice este formato: 8#9-###-####";
    private static final String NOMBRE_MSG = "No se permiten valores numericos ni caracteres no alfabeticos.";
    private static final String USUARIO_MSG = "Debe comenzar con letra, luego cualquier cantidad de numeros, letras, o _";
    private static final String FECHA_MSG = "Debe de especificar su fecha de nacimiento.";

    public static boolean esCorreoValido(EditText editText, boolean required) {
        return esValido(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }


    public static boolean esTelefonoValido(EditText editText, boolean required) {
        return esValido(editText, PHONE_REGEX, TEL_MSG, required);
    }

    public static boolean esNombreValido(EditText editText, boolean required) {
        return esValido(editText, NOMBRE_REGEX, NOMBRE_MSG, required);
    }

    public static boolean esUsuarioValido(EditText editText, boolean required) {
        return esValido(editText, USUARIO_REGEX, USUARIO_MSG, required);
    }

    public static boolean tieneTexto(EditText editText) {
        return hasText(editText);
    }

    public static boolean esPassValido(EditText editText) {
        if (editText.getText().length()<8)
        {
            editText.setError("Contraseña muy corta, debe de tener almenos 8 caracteres.");
            return false;
        }
        if (editText.getText().length()>25)
        {
            editText.setError("Contraseña muy larga, no puede tener mas de 25 caracteres.");
            return false;
        }

        return true;

    }

    private static boolean esValido(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUERIDO_MSG);
            return false;
        }

        return true;
    }
}