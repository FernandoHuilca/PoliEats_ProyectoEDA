package BusinessLogic;

public class Administrador {

    private String nombre;
    private String correo;
    private String contraseña;

    public Administrador(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }
    public Administrador() {
        this.nombre = null;
        this.correo = null;
        this.contraseña = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
}
