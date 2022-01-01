public class Cliente {

    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String dni;
    Fecha diaNacimiento;
    ListaCuentas cuentas;

    public Cliente(String nombre, String apellidos, Fecha fecha, String correoElectronico, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.dni = dni;
        this.cuentas = new ListaCuentas(10);
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public String getDni() {
        return dni;
    }
    public ListaCuentas getCuentas() {
        return cuentas;
    }

    public static boolean validacionNombre(String nombre){

        return (nombre.contains("0") || nombre.contains("1") || nombre.contains("2") || nombre.contains("3") || nombre.contains("4") || nombre.contains("5") || nombre.contains("6") || nombre.contains("7") || nombre.contains("8") || nombre.contains("9"));
    }
    public static boolean validacionApellidos(String apellidos){

        return (apellidos.contains("0") || apellidos.contains("1") || apellidos.contains("2") || apellidos.contains("3") || apellidos.contains("4") || apellidos.contains("5") || apellidos.contains("6") || apellidos.contains("7") || apellidos.contains("8") || apellidos.contains("9"));
    }
    public static boolean validacionCorreo(String correo) {

        return (correo.contains("@upm.es") || correo.contains("@alumnos.upm.es"));
    }
    public static boolean validacionDNI(int dniNum, char dniLet) {

        boolean correcto = false;
        char letraDNI [] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto =(dniNum % 23);

        if(dniLet == letraDNI[resto]){
            correcto = true;
        }

        return correcto;
    }

    public void imprimir(Cliente cliente) {

        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellidos: " + cliente.getApellidos());
        System.out.print("Fecha de nacimiento: ");
        diaNacimiento.imprimir();
        System.out.println("\nDNI: " + cliente.getDni());
        System.out.println("Correo Electronico: " + cliente.getCorreoElectronico());
        System.out.println("Cuentas: "+ cliente.getCuentas() + "\n"); //Mirar println si queda bien

    }


}
